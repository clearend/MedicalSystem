package com.example.medicalsystem.record.controller;

import com.example.medicalsystem.common.ApiResponse;
import com.example.medicalsystem.record.repository.Record;
import com.example.medicalsystem.record.service.RecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("record")
public class RecordController {
    @Resource
    RecordService recordService;


    /**
     * 录入病例
     * @param token
     * @param patientName
     * @param gender
     * @param age
     * @param phoneNumber
     * @param allergyHistory
     * @param symptom
     * @param medicalHistory
     * @param initialDiagnosis
     * @param confirmedResult
     * @param prescription
     * @return
     */
    @RequestMapping(value = "fillRecord",method = RequestMethod.POST)
    public ApiResponse fillRecord(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "patientName") String patientName,
            @RequestParam(value = "gender") Integer gender,
            @RequestParam(value = "age") Integer age,
            @RequestParam(value = "phoneNumber") Long phoneNumber,
            @RequestParam(value = "allergyHistory") String allergyHistory,
            @RequestParam(value = "symptom") String symptom,
            @RequestParam(value = "medicalHistory") String medicalHistory,
            @RequestParam(value = "initialDiagnosis") String initialDiagnosis,
            @RequestParam(value = "confirmedResult") String confirmedResult,
            @RequestParam(value = "prescription") String prescription
    ){
        try {
            recordService.fillRecord(token,patientName,gender,age,phoneNumber,allergyHistory,symptom,medicalHistory,initialDiagnosis,confirmedResult,prescription);
        }catch (Exception e){
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success();
    }

    /**
     * 查看病例
     * @param recordNumber
     * @return
     */
    @RequestMapping(value = "checkRecord",method = RequestMethod.POST)
    public ApiResponse checkRecord(
            @RequestParam(value = "recordNumber") String recordNumber
    ){
        Record record = new Record();
        try {
            record = recordService.checkRecord(recordNumber);
        }catch (Exception e){
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success(record);
    }
}

