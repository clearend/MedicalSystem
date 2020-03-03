package com.example.medicalsystem.record.controller;

import com.example.medicalsystem.common.ApiResponse;
import com.example.medicalsystem.record.repository.Record;
import com.example.medicalsystem.record.repository.RecordRepository;
import com.example.medicalsystem.record.service.RecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("record")
public class RecordController {
    @Resource
    RecordService recordService;

    @Resource
    RecordRepository recordRepository;

    /**
     * 录入病例
     * @param
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
            @RequestParam(value = "userId") String userId,
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
            recordService.fillRecord(userId,patientName,gender,age,phoneNumber,allergyHistory,symptom,medicalHistory,initialDiagnosis,confirmedResult,prescription);
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

    @RequestMapping(value = "modifyRecord",method = RequestMethod.POST)
    public ApiResponse modifyRecord(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "patientName") String patientName,
            @RequestParam(value = "gender") Integer gender,
            @RequestParam(value = "age") Integer age,
            @RequestParam(value = "phoneNumber") Long phoneNumber,
            @RequestParam(value = "allergyHistory") String allergyHistory,
            @RequestParam(value = "symptom") String symptom,
            @RequestParam(value = "medicalHistory") String medicalHistory,
            @RequestParam(value = "initialDiagnosis") String initialDiagnosis,
            @RequestParam(value = "confirmedResult") String confirmedResult,
            @RequestParam(value = "prescription") String prescription,
            @RequestParam(value = "recordNumber") String recordNumber
    ){
        return ApiResponse.success();
    }

//    /**
//     * 通过姓名查找病例
//     * @param name
//     * @return
//     */
//    @RequestMapping(value = "searchRecordByName",method = RequestMethod.POST)
//    public ApiResponse<List<Record>> searchRecordByName(
//            @RequestParam(value = "name") String name
//    ){
//        List<Record> record;
//        try {
//            record = recordService.searchRecordByName(name);
//        }catch (Exception e){
//            return ApiResponse.error(e.getMessage());
//        }
//        return ApiResponse.success(record);
//    }
//
//    /**
//     * 通过时间查找病例
//     * @param startTime
//     * @param endTime
//     * @return
//     */
//    @RequestMapping(value = "searchRecordByTime",method = RequestMethod.POST)
//    public ApiResponse<List<Record>> searchRecordByTimme(
//            @RequestParam(value = "startTime") Long startTime,
//            @RequestParam(value = "endTime") Long endTime
//    ){
//        List<Record> records;
//        try {
//            records = recordService.searchRecordByTime(startTime,endTime);
//        }catch (Exception e) {
//            return ApiResponse.error(e.getMessage());
//        }
//        return ApiResponse.success(records);
//    }
//
//    /**
//     * 通过性别查找病例
//     * @param gender
//     * @return
//     */
//    @RequestMapping(value = "searchRecordByGender",method = RequestMethod.POST)
//    public ApiResponse<List<Record>> searchRecordByGender(
//            @RequestParam(value = "gender") int gender
//    ){
//        List<Record> records;
//        try {
//            records = recordService.searchRecordByGender(gender);
//        }catch (Exception e) {
//            return ApiResponse.error(e.getMessage());
//        }
//        return ApiResponse.success(records);
//    }

    @RequestMapping(value = "searchRecord",method = RequestMethod.POST)
    public ApiResponse<List<Record>> searchRecord(
            @RequestParam(value = "patientName",required = false) String patientName,
            @RequestParam(value = "startTime",required = false) Long startTime,
            @RequestParam(value = "endTime",required = false) Long endTime,
            @RequestParam(value = "gender",required = false) Integer gender,
            @RequestParam(value = "doctorId",required = false) String doctorId,
            @RequestParam(value = "doctorName",required = false) String doctorName,
            @RequestParam(value = "startAge",required = false) Integer startAge,
            @RequestParam(value = "endAge",required = false) Integer endAge,
            @RequestParam(value = "hospital",required = false) String  hospital,
            @RequestParam(value = "department",required = false) String  department,
            @RequestParam(value = "recordNumber",required = false) String recordNumber
    ){
        List<Record> records;
        try {
            records = recordService.searchRecord(patientName,startTime,endTime,gender,doctorId,doctorName,startAge,endAge,hospital,department,recordNumber);
        }catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success(records);
    }


}

