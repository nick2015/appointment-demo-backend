package com.nick.TaroDemo.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.nick.TaroDemo.mapper.AppointmentMapper;
import com.nick.TaroDemo.entity.Appointment;
import com.nick.TaroDemo.util.ResultEnum;
import com.nick.TaroDemo.util.SimpleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    public List<Appointment> getList(String userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderBy("START_TIME", true);
        return appointmentMapper.selectListByQuery(queryWrapper);
    }

    public int deleteById(String id) {
        return appointmentMapper.deleteById(id);
    }

    public Appointment save(Appointment appointment) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.and("USER_ID", appointment.getUserId());
        if (null != appointment.getId()) {
            queryWrapper.ne("ID", appointment.getId());
        }
        List<Appointment> appointments = appointmentMapper.selectListByQuery(queryWrapper);
        boolean conflicted = appointments.stream().anyMatch(item -> appointment.getStartTime().isBefore(item.getEndTime())
                && item.getStartTime().isBefore(appointment.getEndTime()));
        if (conflicted) {
            // time conflict
            log.error("appointment time conflicted");
            throw new SimpleException(ResultEnum.TIME_CONFLICT);
        }
        appointmentMapper.insertOrUpdate(appointment);
        return appointment;
    }

}
