/**
 * copyright Military Traveler, LLC
 */

package com.org.godspeed.utility;


import android.content.Context;
import android.util.Log;

import com.org.godspeed.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;


public class SendFileHttp {
    static final String BOUNDARY = "----------V2ymHFg03ehbqgZCaKO6jy";
    byte[] postBytes = null;
    String urls = null;
    private String version = "";
    private String deviceId = "";
    private String plateform = "";
    private String cipher = "";
    private String userId = "";
    private Context mContext;

//	public SendFileHttp(String url, String jsonString, String fileField,
//			String fileName, String fileType, byte[] fileBytes)
//			throws Exception {
//		urls = url;
//		String boundary = getBoundaryString();
//		String boundaryMessage = getBoundaryMessage(boundary, jsonString,
//				fileField, fileName, fileType);
//		String endBoundary = "\r\n--" + boundary + "--\r\n";
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		bos.write(boundaryMessage.getBytes());
//		bos.write(fileBytes);
//		bos.write(endBoundary.getBytes());
//		this.postBytes = bos.toByteArray();
//		bos.close();
//
//	}

    public SendFileHttp(String url, Hashtable params, String fileField,
                        String fileName, String fileType, byte[] fileBytes, Context context, String userId)
            throws Exception {
        System.out.println("length ---------------------------------- : " + fileBytes.length);
//		this.version = version;
//		this.plateform=plateform;
//		this.deviceId= deviceId;
//		this.cipher=cipher;
        this.userId = userId;
        mContext = context;
        urls = url;
        String boundary = getBoundaryString();
        String boundaryMessage = getBoundaryMessage(boundary, params,
                fileField, fileName, fileType);
        String endBoundary = "\r\n--" + boundary + "--\r\n";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(boundaryMessage.getBytes());
        bos.write(fileBytes);
        bos.write(endBoundary.getBytes());
        this.postBytes = bos.toByteArray();
        bos.close();

    }

    private static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the
         * BufferedReader.readLine() method. We iterate until the BufferedReader
         * return null which means there's no more data to read. Each line will
         * appended to a StringBuilder and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                Log.v("                ", "                " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


//	String getBoundaryMessage(String boundary, String jsonString,
//			String fileField, String fileName, String fileType) {
//		
//		StringBuffer res = new StringBuffer("--").append(boundary).append(
//				"\r\n");
//		String key = "data";
//		String value = jsonString;
//
//			res.append("Content-Disposition: form-data; name=\"").append(key)
//					.append("\"\r\n").append("\r\n").append(value).append(
//							"\r\n").append("--").append(boundary)
//					.append("\r\n");
//		res.append("Content-Disposition: form-data; name=\"").append(fileField)
//				.append("\"; filename=\"").append(fileName).append("\"\r\n")
//				.append("Content-Type: ").append(fileType).append("\r\n\r\n");
//
//		return res.toString();
//	}

    String getBoundaryString() {
        return BOUNDARY;
    }

    String getBoundaryMessage(String boundary, Hashtable params,
                              String fileField, String fileName, String fileType) {

        StringBuffer res = new StringBuffer("--").append(boundary).append(
                "\r\n");

        Enumeration keys = params.keys();

        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) params.get(key);

            res.append("Content-Disposition: form-data; name=\"").append(key)
                    .append("\"\r\n").append("\r\n").append(value).append(
                    "\r\n").append("--").append(boundary)
                    .append("\r\n");
        }
        res.append("Content-Disposition: form-data; name=\"").append(fileField)
                .append("\"; filename=\"").append(fileName).append("\"\r\n")
                .append("Content-Type: ").append(fileType).append("\r\n\r\n");

        return res.toString();
    }

    public byte[] send() throws Exception {
        HttpURLConnection hc = null;
        InputStream is = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] res = null;

        try {
//			URL url = new URL("http://militarytravelerapp.com/mildevelopment/api/establishments.php?");
            URL url = new URL(urls);
            // Open a HTTP connection to the URL
            hc = (HttpURLConnection) url.openConnection();
            // Allow Inputs
            hc.setDoInput(true);
            // Allow Outputs
            hc.setDoOutput(true);
            // Don't use a cached copy.
            hc.setUseCaches(false);
            hc.setRequestProperty(mContext.getString(R.string.user_id_tag), userId);
//			hc.setRequestProperty(mContext.getString(R.string.Device_Id_Parameter), deviceId);
//			hc.setRequestProperty(mContext.getString(R.string.PlatformParameter), plateform);
//			hc.setRequestProperty(mContext.getString(R.string.CipherParameter), cipher);
            hc.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + getBoundaryString());
            hc.setRequestProperty("Connection", "Keep-Alive");
            hc.setRequestMethod("POST");
            OutputStream dout = hc.getOutputStream();
            dout.write(postBytes);
            dout.close();
            int ch;
            is = hc.getInputStream();

            while ((ch = is.read()) != -1) {
                bos.write(ch);
            }
            res = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception...." + e.getMessage());
        } finally {
            try {
                if (bos != null)
                    bos.close();

                if (is != null)
                    is.close();

                if (hc != null)
                    hc.disconnect();
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("Exception...." + e2.getMessage());
            }
        }
        return res;
    }

}

