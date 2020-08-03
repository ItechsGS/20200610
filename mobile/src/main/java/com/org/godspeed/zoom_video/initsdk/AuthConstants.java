package com.org.godspeed.zoom_video.initsdk;

public interface AuthConstants {

    // TODO Change it to your web domain
    String WEB_DOMAIN = "zoom.us";

    // TODO Change it to your APP Key
    String SDK_KEY = "";

    // TODO Change it to your APP Secret
    String SDK_SECRET = "";

    /**
     * We recommend that, you can generate jwttoken on your own server instead of hardcore in the code.
     * We hardcore it here, just to run the demo.
     *
     * You can generate a jwttoken on the https://jwt.io/
     * with this payload:
     * {
     *     "appKey": "string", // app key
     *     "iat": long, // access token issue timestamp
     *     "exp": long, // access token expire time
     *     "tokenExp": long // token expire time
     * }
     */

}
