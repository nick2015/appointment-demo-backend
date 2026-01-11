package com.nick.TaroDemo.controller;

import com.nick.TaroDemo.dto.ResultEntity;
import com.nick.TaroDemo.entity.Appointment;
import com.nick.TaroDemo.service.AppointmentService;
import com.nick.TaroDemo.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/list")
    public ResultEntity<List<Appointment>> getAppointment(@RequestParam("userId")String userId) {
        return new ResultEntity<>(appointmentService.getList(userId));
    }

    @PostMapping("/save")
    public ResultEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
        return new ResultEntity(appointmentService.save(appointment));
    }

    @DeleteMapping("/delete")
    public ResultEntity deleteAppointment(@RequestParam("id")String id) {
        appointmentService.deleteById(id);
        return new ResultEntity(ResultEnum.SUCCESS);
    }
}
