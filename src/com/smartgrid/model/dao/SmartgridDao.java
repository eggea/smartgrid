package com.smartgrid.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.smartgrid.model.vo.DeviceVo;
import com.smartgrid.model.vo.MeteringVo;

/* Classe DAO contém os métodos que recebem valores
 * faz a conexão com o banco
 * realiza a busca, armazena a busca em um objeto pojo 
 * e retorna esse objeto pojo
 */
public class SmartgridDao extends MysqlHelper {

	// Recebe ID do dispositivo
	// Retorna todas linhas do banco de dados deste dispositivo
	public List<MeteringVo> getMeteringAll(Integer id) {
		List<MeteringVo> muList = new ArrayList<MeteringVo>();

		try {
			conn = openConnection();
			prstmt = conn
					.prepareStatement("select * from tbmetering where iddevice=? order by meteringDate asc");
			prstmt.setInt(1, id);
			rs = prstmt.executeQuery();
			while (rs.next()) {
				MeteringVo mu = new MeteringVo();
				mu.setIdDevice(rs.getInt("idDevice"));
				mu.setMeteringDate(rs.getTimestamp("meteringDate").toString());
				mu.setConsumption(rs.getFloat("consumption"));
				mu.setConsumptionPrice(rs.getFloat("consumptionPrice"));
				mu.setGeneration(rs.getFloat("generation"));
				mu.setGenerationPrice(rs.getFloat("generationPrice"));
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

	// Retorna todas as amostras no banco do ID
	public List<MeteringVo> getMeteringDailyAll(Integer id, String date){
		
		List<MeteringVo> muList = new ArrayList<MeteringVo>();			
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
			
			// Funcao auxiliar que recebe resultset e retorna objeto preenchido
			muList = SmartgridUtil.mapResult(rs); 
			 
			// Fecha conexão e variaveis
			closeConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return muList;				
	}	

	// Busca os valores em tempo real
	public List<MeteringVo> getMeteringNow(Integer id) {
		List<MeteringVo> muList = new ArrayList<MeteringVo>();			
		try {
			conn = openConnection();
			prstmt = conn
					.prepareStatement("call getMeteringNowTest(?)");  // Utilizando procedure de teste 
			prstmt.setInt(1, id );
			rs = prstmt.executeQuery();  // retorna um valor
			
			// Funcao auxiliar que recebe resultset e preenche o objeto
			muList = SmartgridUtil.mapResult(rs); 
			 
			// Fecha conexão e variaveis
			closeConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return muList;				
	}
	
	// Busca diaria - retorna 24 valores 
	public List<MeteringVo> getMeteringDaily(Integer id, String date){
		
	List<MeteringVo> muListTemp = new ArrayList<MeteringVo>();	
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
									
			// Funcao auxiliar que recebe resultset e retorna uma lista de objeto preenchido
			muListTemp = SmartgridUtil.mapResult(rs); 
			
			// Fecha conexão e variaveis
			closeConnection();						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Arrumo os valores e retorno o valor diario
		List <MeteringVo> muListDaily = new ArrayList<MeteringVo>();			
		int i=0;
		while( i < muListTemp.size()){
			Integer idtmp = null;
			String datetmp = null;
			Float consumption=0F;
			Float consumptionPrice=0F;
			Float generation=0F;
			Float generationPrice=0F;
			
			for(int j=0;j<12;j++){
				// guarda ultimo horario e iddevice				
				datetmp = muListTemp.get(i).getMeteringDate();
				idtmp = muListTemp.get(i).getIdDevice();
				//System.out.println(datetmp);

				// acumula 12 amostras
				consumption+=muListTemp.get(i).getConsumption();				
				consumptionPrice+=muListTemp.get(i).getConsumptionPrice();
				generation+=muListTemp.get(i).getGeneration();
				generationPrice+=muListTemp.get(i).getGenerationPrice();
				i++;
			}
			System.out.println("i: " + i + " consumo:" + consumption);
			MeteringVo vo = new MeteringVo();
			vo.setIdDevice(idtmp);
			vo.setMeteringDate(datetmp);
			vo.setConsumption(consumption);
			vo.setConsumptionPrice(consumptionPrice);
			vo.setGeneration(generation);
			vo.setGenerationPrice(generationPrice);
			muListDaily.add(vo);
		}				
		// Retorna o objeto
		return muListDaily;				
	}	

	// Busca diaria acumulada - retorna 24 valores	
	// IMPLEMENTAR
	
	// Busca semanal - retorna 7 valores 
	public List<MeteringVo> getMeteringWeekly(Integer id, String date){
	
	List<MeteringVo> muListTemp = new ArrayList<MeteringVo>();	
		try {
			conn = openConnection();
			prstmt = conn
					.prepareStatement("select * from tbmetering " +
							"where iddevice=? " +
							"and meteringDate " +
							"between ? and date_add(?,INTERVAL 7 DAY) " +
							"order by meteringDate asc"); 
			prstmt.setInt(1, id );
			prstmt.setString(2, date + " 00:05:00 ");
			prstmt.setString(3, date + " 00:00:00 ");			
			rs = prstmt.executeQuery();  // retorna 288 medicoes
									
			// Funcao auxiliar que recebe resultset e retorna uma lista de objeto preenchido
			muListTemp = SmartgridUtil.mapResult(rs); 
			
			System.out.println("NO OBJETOS: "+ muListTemp.size());
			
			// Fecha conexão e variaveis
			closeConnection();						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Arrumo os valores e retorno o valor semanal
		List <MeteringVo> muListWeekly = new ArrayList<MeteringVo>();			
		int i=0;
		while( i < muListTemp.size()){
			Integer idtmp = null;
			String datetmp = null;
			Float consumption=0F;
			Float consumptionPrice=0F;
			Float generation=0F;
			Float generationPrice=0F;
			
			for(int j=0;j<288;j++){
				// guarda ultimo horario e iddevice				
				datetmp = muListTemp.get(i).getMeteringDate();
				idtmp = muListTemp.get(i).getIdDevice();
				//System.out.println(datetmp);

				// acumula 84 amostras
				consumption+=muListTemp.get(i).getConsumption();				
				consumptionPrice+=muListTemp.get(i).getConsumptionPrice();
				generation+=muListTemp.get(i).getGeneration();
				generationPrice+=muListTemp.get(i).getGenerationPrice();
				i++;
			}
			System.out.println("i: " + i + " consumo:" + consumption);
			MeteringVo vo = new MeteringVo();
			vo.setIdDevice(idtmp);
			vo.setMeteringDate(datetmp);
			vo.setConsumption(consumption);
			vo.setConsumptionPrice(consumptionPrice);
			vo.setGeneration(generation);
			vo.setGenerationPrice(generationPrice);
			muListWeekly.add(vo);
		}				
		// Retorna o objeto
		return muListWeekly;		
}

	// Busca semanal acumulada 
	// IMPLEMENTAR
	
	// Busca mental - retorna 30 valores 
	public List<MeteringVo> getMeteringMonthly(Integer id, String date){
		
	List<MeteringVo> muListTemp = new ArrayList<MeteringVo>();	
		try {
			conn = openConnection();
			prstmt = conn
					.prepareStatement("select * from tbmetering " +
							"where iddevice=? " +
							"and meteringDate " +
							"between date_format(? ,'%Y-%m-01 00:05:00') " +
							"and date_add(last_day(?),interval 1 day) " +
							"order by meteringDate asc"); 
			prstmt.setInt(1, id );
			prstmt.setString(2, date + " 00:05:00 ");
			prstmt.setString(3, date);
			rs = prstmt.executeQuery();  // retorna todas medicoes ate o fim do mes
									
			// Funcao auxiliar que recebe resultset e retorna uma lista de objeto preenchido
			muListTemp = SmartgridUtil.mapResult(rs); 
			
			System.out.println("NO OBJETOS: "+ muListTemp.size());
			
			// Fecha conexão e variaveis
			closeConnection();						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Arrumo os valores e retorno o valor semanal
		List <MeteringVo> muListWeekly = new ArrayList<MeteringVo>();			
		int i=0;
		while( i < muListTemp.size()){
			Integer idtmp = null;
			String datetmp = null;
			Float consumption=0F;
			Float consumptionPrice=0F;
			Float generation=0F;
			Float generationPrice=0F;
			
			for(int j=0;j<288;j++){
				// guarda ultimo horario e iddevice				
				datetmp = muListTemp.get(i).getMeteringDate();
				idtmp = muListTemp.get(i).getIdDevice();
				//System.out.println(datetmp);

				// acumula 84 amostras
				consumption+=muListTemp.get(i).getConsumption();				
				consumptionPrice+=muListTemp.get(i).getConsumptionPrice();
				generation+=muListTemp.get(i).getGeneration();
				generationPrice+=muListTemp.get(i).getGenerationPrice();
				i++;
			}			
			MeteringVo vo = new MeteringVo();
			vo.setIdDevice(idtmp);
			vo.setMeteringDate(datetmp);
			vo.setConsumption(consumption);
			vo.setConsumptionPrice(consumptionPrice);
			vo.setGeneration(generation);
			vo.setGenerationPrice(generationPrice);
			muListWeekly.add(vo);
		}				
		// Retorna o objeto
		return muListWeekly;		
}	

	// Busca mensal acumulada
	// IMPLEMENTAR
	
	// Metodo que insere na base o agendamento dos smartplugs
	// IMPLEMENTAR
	
	// Metodo que faz autenticacao
	// IMPLEMENTAR (OPCIONAL)
	
	
	// Lista todos os dispositivos
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
	
	// Busca a tarifa em um determinado datetime
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
