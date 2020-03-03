package com.example.medicalsystem.record.service;

import com.example.medicalsystem.record.repository.Record;
import com.example.medicalsystem.record.repository.RecordRepository;
import com.example.medicalsystem.user.repository.User;
import com.example.medicalsystem.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service

public class RecordService {
    @Resource
    RecordRepository recordRepository;

    @Resource
    UserRepository userRepository;

    /**
     * 录入病例
     * @param userId
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
     * @throws Exception
     */
    public void fillRecord(String userId, String patientName, Integer gender, Integer age, Long phoneNumber, String allergyHistory, String symptom, String medicalHistory, String initialDiagnosis, String confirmedResult, String prescription) throws Exception {
         User user = userRepository.findByUserId(userId);
         Record record = new Record();
        if(record!=null)
        {
            try {
                record.setUserId(userId);
                record.setRecordNumber("AS"+System.currentTimeMillis());
                record.setHospital(userRepository.findByUserId(userId).getWorkUnit());
                record.setUserName(userRepository.findByUserId(userId).getUserName());
                record.setPatientName(patientName);
                record.setGender(gender);
                record.setAge(age);
                record.setDepartment(userRepository.findByUserId(userId).getDepartment());
                record.setPhoneNumber(phoneNumber);
                record.setAllergyHistory(allergyHistory);
                record.setSymptom(symptom);
                record.setMedicalHistory(medicalHistory);
                record.setInitialDiagnosis(initialDiagnosis);
                record.setConfirmedResult(confirmedResult);
                record.setPrescription(prescription);
                record.setCreateTime(System.currentTimeMillis());
                record.setUpdateTime(System.currentTimeMillis());
                recordRepository.save(record);
            }catch (Exception e)
            {
                e.printStackTrace();
                throw new Exception("病例录入失败");
            }
        }
    }

    /**
     * 查看病例
     * @param recordNumber
     * @return
     * @throws Exception
     */
    public Record checkRecord(String recordNumber) throws Exception {
        try {
            return recordRepository.findByRecordNumber(recordNumber);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("病例查询失败");
        }
    }
}
