package com.fpt.myweb.controller;

import com.fpt.myweb.dto.request.UserRequet;
import com.fpt.myweb.dto.response.ChartStaffRes;
import com.fpt.myweb.dto.response.CommonRes;
import com.fpt.myweb.dto.response.DetailOneDayRes;
import com.fpt.myweb.dto.response.UserRes;
import com.fpt.myweb.exception.AppException;
import com.fpt.myweb.exception.ErrorCode;
import com.fpt.myweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/staff")
public class StaffController {
    @Autowired
    private UserService userService;
    @GetMapping("/allPatientForStaff")// fomat sang DTO trả về dữ liệu
    public ResponseEntity<CommonRes> getAll(@PathParam("page") Integer page,@PathParam("villageId") Long villageId) {
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            List<UserRequet> userPage = userService.getAllPatientForStaff1(villageId,page);
            UserRes userRes = new UserRes();
            userRes.setUserRequets(userPage);
            userRes.setTotal(userPage.size());
            commonRes.setData(userRes);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }

    @GetMapping("/searchTextForStaff")// fomat sang DTO trả về dữ liệu
    public ResponseEntity<CommonRes> getAllByText(@PathParam("page") Integer page,@PathParam("key") String key, @PathParam("villageId") Long villageId) {
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            List<UserRequet> userRequets = userService.searchByTextForStaff(key, villageId,page);
            UserRes userRes = new UserRes();
            userRes.setUserRequets(userRequets);
            userRes.setTotal(userRequets.size());
            commonRes.setData(userRes);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }



    @GetMapping("/getNewPatientOneDay")// fomat sang DTO trả về dữ liệu
    public ResponseEntity<CommonRes> getNewPatientOneDay(@PathParam("page") Integer page,@PathParam("time") String time,@PathParam("villageId") Long villageId) {
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            List<UserRequet> userRequets = userService.getNewPatientOneDay1(time,villageId,page);
            UserRes userRes = new UserRes();
            userRes.setUserRequets(userRequets);
            userRes.setTotal(userRequets.size()); //                                                          Chỗ ni chưa lấy đc tổng để phân trang
            commonRes.setData(userRes);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }
    @GetMapping("/getDetaiOneDay")// fomat sang DTO trả về dữ liệu
    public ResponseEntity<CommonRes> getDetaiOneDay(@PathParam("time") String time,@PathParam("villageId") Long villageId) {
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            List<UserRequet> newOneday = userService.getNewPatientOneDay(time,villageId);
            List<UserRequet> curedOneday = userService.getCuredPatientOneDay(time,villageId);
            List<UserRequet> current = userService.getAllPatientForStaff(villageId);
            DetailOneDayRes detailOneDayRes = new DetailOneDayRes();
            detailOneDayRes.setTotalNewF0(newOneday.size());
            detailOneDayRes.setTotalCured(curedOneday.size());
            detailOneDayRes.setTotalCurrentF0(current.size());
            commonRes.setData(detailOneDayRes);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }

    @GetMapping("/allPatientCuredForStaff")// fomat sang DTO trả về dữ liệu
    public ResponseEntity<CommonRes> getAllCured(@PathParam("page") Integer page,@PathParam("villageId") Long villageId) {
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            List<UserRequet> userPage = userService.getAllPatientCuredForStaff1(villageId,page);
            UserRes userRes = new UserRes();
            userRes.setUserRequets(userPage);
            userRes.setTotal(userPage.size());
            commonRes.setData(userRes);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }

    @GetMapping("/chartForStaff")// fomat sang DTO trả về dữ liệu
    public ResponseEntity<CommonRes> chartForStaff(@PathParam("startDate")String startDate, @PathParam("endDate") String endDate,@PathParam("villageId") Long villageId) {
        CommonRes commonRes = new CommonRes();
        try {
            commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
            commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
            List<ChartStaffRes> chartStaffRes = userService.getChartForStaff(startDate,endDate,villageId);
            commonRes.setData(chartStaffRes);
        } catch (Exception e){
            commonRes.setResponseCode(ErrorCode.INTERNAL_SERVER_ERROR.getKey());
            commonRes.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return ResponseEntity.ok(commonRes);
    }

}
