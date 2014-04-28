package com.smartgrid.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.smartgrid.model.dao.SmartgridDao;
import com.smartgrid.model.vo.DeviceVo;
import com.smartgrid.model.vo.MeteringVo;

public class SmartgridUtil {

	public static List<MeteringVo> mapResult(ResultSet rs) throws SQLException {
		List<MeteringVo> muListTemp = new ArrayList<MeteringVo>();
		while (rs.next()) {
			MeteringVo muTemp = new MeteringVo();
			muTemp.setIdDevice(rs.getInt("idDevice"));
			muTemp.setMeteringDate(rs.getTimestamp("meteringDate").toString());
			muTemp.setConsumption(rs.getFloat("consumption"));
			muTemp.setConsumptionPrice(rs.getFloat("consumptionPrice"));
			muTemp.setGeneration(rs.getFloat("generation"));
			muTemp.setGenerationPrice(rs.getFloat("generationPrice"));
			muTemp.setVoltage(rs.getFloat("voltage"));
			muTemp.setCurrent(rs.getFloat("current"));
			muTemp.setPower(rs.getFloat("power"));
			muTemp.setBattery(rs.getFloat("battery"));
			muListTemp.add(muTemp);
		}
		return muListTemp;
	}
}
