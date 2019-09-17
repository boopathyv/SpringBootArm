package com.aram.connect.dataObjects;

public class PrelimsTestUploadReq {

    public Long timeStamp;

    public Long studentId;

    public Long prelimsTestId;

    public PrelimsTestAnswerReq[] answers;
}

class PrelimsTestAnswerReq {

    public Long questionId;

    public String answer;
}