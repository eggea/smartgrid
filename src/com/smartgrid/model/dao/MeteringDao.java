package com.smartgrid.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.smartgrid.model.vo.MeteringVo;

public class MeteringDao extends SqliteHelper {
	MeteringVo mt;

	public MeteringVo getMeteringDaily(Integer idDevice, String strdatetime) {

		connection = openConnection();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from metering where idDevice=5");

			Float f = 0F;
			List<Float> metering = new ArrayList<Float>();
			mt = new MeteringVo();
			while (rs.next()) {
				f = rs.getFloat("consumption");
				metering.add(f);
				//System.out.println("Medicao: " + f);
			}
			mt.setDeviceName("Teste");
			mt.setConsumptionValue(metering);

			closeConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Retorna o objeto
		return mt;
	}
}
