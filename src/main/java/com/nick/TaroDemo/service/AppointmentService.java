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
import static com.nick.TaroDemo.entity.table.AppointmentTableDef.APPOINTMENT;

@Service
@Slf4j
public class AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    public List<Appointment> getList(String userId) {
        QueryWrapper query = QueryWrapper.create()
                .select()
                .from(Appointment.class) // APPOINTMENT 是自动生成的 TableDef 类
                .where(APPOINTMENT.USER_ID.eq(userId))
                .orderBy(APPOINTMENT.START_TIME.asc());
        return appointmentMapper.selectListByQuery(query);
    }

    public int deleteById(String id) {
        return appointmentMapper.deleteById(id);
    }

    public Appointment save(Appointment appointment) {
        QueryWrapper query = QueryWrapper.create()
                .select()
                .from(Appointment.class) // APPOINTMENT 是自动生成的 TableDef 类
                .where(APPOINTMENT.USER_ID.eq(appointment.getUserId()));
        if (null != appointment.getId()) {
            query.and(APPOINTMENT.ID.ne(appointment.getId()));
        }

        List<Appointment> appointments = appointmentMapper.selectListByQuery(query);
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
