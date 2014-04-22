package com.smartgrid.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.smartgrid.model.vo.DeviceVo;
import com.smartgrid.model.vo.MeteringUnit;
import com.smartgrid.model.vo.MeteringVo;

/* Classe DAO contém os métodos que recebem valores
 * faz a conexão com o banco
 * realiza a busca, armazena a busca em um objeto pojo 
 * e retorna esse objeto pojo
 */
public class SmartgridDao extends MysqlHelper {

	public MeteringVo getMeteringv1(Integer id) {
		MeteringVo mtvo = new MeteringVo();
		List<Float> consumolist = new ArrayList<Float>();
		List<Float> geralist = new ArrayList<Float>();

		try {
			conn = openConnection();
			prstmt = conn
					.prepareStatement("select * from tbmetering where iddevice=?");
			prstmt.setInt(1, id);
			rs = prstmt.executeQuery();
			while (rs.next()) {
				consumolist.add(rs.getFloat("consumption"));
				geralist.add(rs.getFloat("generation"));
			}
			// Cria objeto com o conteudo
			mtvo.setConsumptionValue(consumolist);
			mtvo.setGenerationValue(geralist);

			// Fecha conexão e variaveis
			closeConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mtvo;
	}

	public List<MeteringUnit> getMetering(Integer id) {
		List<MeteringUnit> muList = new ArrayList<MeteringUnit>();

		try {
			conn = openConnection();
			prstmt = conn
					.prepareStatement("select * from tbmetering where iddevice=? order by meteringDate asc");
			prstmt.setInt(1, id);
			rs = prstmt.executeQuery();
			while (rs.next()) {
				MeteringUnit mu = new MeteringUnit();
				mu.setIdDevice(rs.getInt("idDevice"));
				mu.setMeteringDate(rs.getTimestamp("meteringDate").toString());
				mu.setConsumption(rs.getFloat("consumption"));
				mu.setGeneration(rs.getFloat("generation"));
				mu.setVoltage(rs.getFloat("voltage"));
				mu.setCurrent(rs.getFloat("current"));
				mu.setPower(rs.getFloat("power"));
				mu.setBattery(rs.getFloat("battery"));
				muList.add(mu);
			}
			// Fecha conexão e variaveis
			closeConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return muList;
	}

	// Retorna 288 valores
	public List<MeteringUnit> getMeteringDailyAll(Integer id, String date){
		
		List<MeteringUnit> muList = new ArrayList<MeteringUnit>();			
		try {
			conn = openConnection();
			prstmt = conn
					.prepareStatement("select * from tbmetering " +
							"where iddevice=? " +
							"and meteringDate " +
							"between ? and date_add(?,INTERVAL 1 DAY) " +
							"order by meteringDate asc"); 
			prstmt.setInt(1, id );
			prstmt.setString(2, date + " 00:05:00 ");
			prstmt.setString(3, date + " 00:00:00 ");			
			rs = prstmt.executeQuery();  // retorna 288 medicoes
			
			while (rs.next()) {
				MeteringUnit mu = new MeteringUnit();
				mu.setIdDevice(rs.getInt("idDevice"));
				mu.setMeteringDate(rs.getTimestamp("meteringDate").toString());
				mu.setConsumption(rs.getFloat("consumption"));
				
				Float f = getPrice(mu.getMeteringDate());
				System.out.println(mu.getMeteringDate()+ " " + f);
				//mu.setConsumptionPrice(mu.getConsumption());
				mu.setGeneration(rs.getFloat("generation"));				
				mu.setVoltage(rs.getFloat("voltage"));
				mu.setCurrent(rs.getFloat("current"));
				mu.setPower(rs.getFloat("power"));
				mu.setBattery(rs.getFloat("battery"));
				muList.add(mu);
			}
			// Fecha conexão e variaveis
			closeConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return muList;				
	}	
	

	// Busca diaria retorna 24 valores
public List<MeteringUnit> getMeteringDaily(Integer id, String date){
		
		List<MeteringUnit> muList = new ArrayList<MeteringUnit>();			
		try {
			conn = openConnection();
			prstmt = conn
					.prepareStatement("select * from tbmetering " +
							"where iddevice=? " +
							"and meteringDate " +
							"between ? and date_add(?,INTERVAL 1 DAY) " +
							"order by meteringDate asc"); 
			prstmt.setInt(1, id );
			prstmt.setString(2, date + " 00:05:00 ");
			prstmt.setString(3, date + " 00:00:00 ");			
			rs = prstmt.executeQuery();  // retorna 288 medicoes
			
			while (rs.next()) {
				MeteringUnit mu = new MeteringUnit();
				mu.setIdDevice(rs.getInt("idDevice"));
				mu.setMeteringDate(rs.getTimestamp("meteringDate").toString());
				mu.setConsumption(rs.getFloat("consumption"));
				
				Float price = getPrice(mu.getMeteringDate());
				System.out.println(mu.getMeteringDate()+ " " + price);
				mu.setConsumptionPrice((mu.getConsumption()/1000)*price);
				mu.setGeneration(rs.getFloat("generation"));				
				mu.setVoltage(rs.getFloat("voltage"));
				mu.setCurrent(rs.getFloat("current"));
				mu.setPower(rs.getFloat("power"));
				mu.setBattery(rs.getFloat("battery"));
				muList.add(mu);
			}

			List<MeteringUnit> mudayList = new ArrayList<MeteringUnit>();
					
			int i=0;
			while( i < muList.size()){
				Float consumo=0F;
				Float preco=0F;
				for(int j=0;j<12;j++){
					consumo+=muList.get(i).getConsumption();
					preco+=muList.get(j).getConsumptionPrice();
					i++;
				}
				System.out.println("no:" + i + " consumo:" + consumo + " preco:" + preco);
			}
			
			
			// Fecha conexão e variaveis
			closeConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return muList;				
	}	
	public List<DeviceVo> listDevices() {

		List<DeviceVo> deviceList = new ArrayList<DeviceVo>();

		try {
			conn = openConnection();
			prstmt = conn.prepareStatement("select * from tbdevice");
			rs = prstmt.executeQuery();
			while (rs.next()) {
				DeviceVo dev = new DeviceVo();
				dev.setIdDevice(rs.getInt("idDevice"));
				dev.setName(rs.getString("name"));
				dev.setDescription(rs.getString("description"));
				dev.setDeviceType(rs.getString("deviceType"));
				dev.setIpaddress(rs.getString("ipaddress"));
				dev.setPort(rs.getInt("port"));
				deviceList.add(dev);
			}

			// Fecha conexão e variaveis
			closeConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deviceList;
	}

	
	// Busca o preco no banco de dados
	public Float getPrice(String datetime) {
		Float price = null;

		// Gambiarra separar datetime em date e time
		String[] split = datetime.split(" ");
		String dia = split[0];
		String hora = split[1];

		try {
			Connection conn = openConnection();
			PreparedStatement prstmt = conn.prepareStatement("call getPrice(?,?)");
			prstmt.setString(1, dia);
			prstmt.setString(2, hora);
			ResultSet rs = prstmt.executeQuery();
			while (rs.next()) {
				price = rs.getFloat("price");
			}
			// Fecha conexão e variaveis
			conn.close();
			prstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}

}
