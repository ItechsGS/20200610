
package com.org.godspeed.response_JsonS.GetPurchaseHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetPurchaseHistory implements Serializable {

    private final static long serialVersionUID = 6323318216512638383L;
    @SerializedName("txn_id")
    @Expose
    private String txnId;
    @SerializedName("PaymentMethod")
    @Expose
    private String paymentMethod;
    @SerializedName("PayerStatus")
    @Expose
    private String payerStatus;
    @SerializedName("PayerMail")
    @Expose
    private String payerMail;
    @SerializedName("Total")
    @Expose
    private String total;
    @SerializedName("SubTotal")
    @Expose
    private String subTotal;
    @SerializedName("Tax")
    @Expose
    private String tax;
    @SerializedName("Payment_state")
    @Expose
    private String paymentState;
    @SerializedName("CreateTime")
    @Expose
    private String createTime;
    @SerializedName("UpdateTime")
    @Expose
    private String updateTime;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("program_id")
    @Expose
    private String programId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("program_name")
    @Expose
    private String programName;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * No args constructor for use in serialization
     */
    public GetPurchaseHistory() {
    }

    /**
     * @param lastName
     * @param description
     * @param tax
     * @param updateTime
     * @param subTotal
     * @param payerStatus
     * @param userId
     * @param firstName
     * @param total
     * @param payerMail
     * @param createTime
     * @param programName
     * @param paymentMethod
     * @param id
     * @param paymentState
     * @param programId
     * @param txnId
     */
    public GetPurchaseHistory(String txnId, String paymentMethod, String payerStatus, String payerMail, String total, String subTotal, String tax, String paymentState, String createTime, String updateTime, String id, String programId, String userId, String firstName, String lastName, String programName, String description) {
        super();
        this.txnId = txnId;
        this.paymentMethod = paymentMethod;
        this.payerStatus = payerStatus;
        this.payerMail = payerMail;
        this.total = total;
        this.subTotal = subTotal;
        this.tax = tax;
        this.paymentState = paymentState;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.id = id;
        this.programId = programId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.programName = programName;
        this.description = description;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPayerStatus() {
        return payerStatus;
    }

    public void setPayerStatus(String payerStatus) {
        this.payerStatus = payerStatus;
    }

    public String getPayerMail() {
        return payerMail;
    }

    public void setPayerMail(String payerMail) {
        this.payerMail = payerMail;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
