package com.example.admin.task1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thapovan.android.commonutils.text.TextUtil;

import java.util.List;

/**
 * Created by Admin on 8/30/2017.
 */

public class GenericResponse {

    @Expose
    @SerializedName("success")
    public int success;

    @Expose
    @SerializedName("message")
    public String message;

    @Expose
    @SerializedName("error")
    public String error;

    @Expose
    @SerializedName("parameters")
    private List<Parameters> parameters;

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message = msg;
    }

    public boolean isSuccess() {
        return this.success == 1;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getErrorMsg() {
       /* if(AppUtil.isListNotEmpty(parameters) && parameters.getAllProducts(0) != null
                && TextUtil.isValidString(parameters.getAllProducts(0).getMessage())){
            return parameters.getAllProducts(0).getMessage();
        }*/
        if (TextUtil.isValidString(error)) {
            return error;
        }
        if (TextUtil.isValidString(message)) {
            return message;
        }
        return "Something went wrong";
    }


    public List<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameters> parameters) {
        this.parameters = parameters;
    }

    private class Parameters {

        @Expose
        @SerializedName("desc")
        private String desc;

        @Expose
        @SerializedName("field")
        private String field;

        @Expose
        @SerializedName("message")
        private String message;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
