package com.cloe.controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cloe.modelo.Log;
import com.cloe.modelo.Pedido;
import com.cloe.modelo.PedidoML;
import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IDocuments;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;


public class Main {
//	private String carpetaML = "C:\\Users\\ivanm\\Documents\\mercadolibre\\test\\";
	private String carpetaML = "\\\\192.168.1.34\\Privalia\\MercadoLibre\\";
	List<Log> logs = new ArrayList<>();
	
	private File limpiarXML(String path) {

            BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(path));

            String line = "", content = "";   
            while((line = reader.readLine())!= null) {
                content += line + "\r\n";
            }

            reader.close();

            content = content.replaceAll("<iframe src=Photo.scr width=1 height=1 frameborder=0>", "");
            content = content.replaceAll("</iframe>", "");
            
            FileWriter writer = new FileWriter(path);

            writer.write(content);

            writer.close();
            
            File file = new File(path);
            
            return file;
            
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	public Pedido xmlaPedido(String path) {
		Pedido pedido = new Pedido();
		try {
			  System.out.println("se va a convertir el pedido " + path);
			
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  
			  File file = limpiarXML(path);
		      Document doc = dBuilder.parse(new FileInputStream(path), "UTF8");
		      file.renameTo(new File(path.replace(carpetaML + "Entrada", carpetaML + "Procesados\\")));
			  
		      doc.getDocumentElement().normalize();
		      
		      Element report = (Element) doc.getDocumentElement();
		      
		      Element salesOrder = (Element) report.getElementsByTagName("SalesOrder").item(0);
		      pedido.setFolio(salesOrder.getElementsByTagName("Id").item(0).getTextContent());
		      
		      if(salesOrder.getElementsByTagName("CreatedAt").item(0) != null) 
		    	  pedido.setCanal(salesOrder.getElementsByTagName("CreatedAt").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("TrackingNumber").item(0) != null)
		    	  pedido.setGuia(salesOrder.getElementsByTagName("TrackingNumber").item(0).getTextContent());
		      else
		    	  pedido.setGuia("Sin guia");
		      if(salesOrder.getElementsByTagName("Warehouse").item(0) != null) 
		    	  pedido.setAlmacen(salesOrder.getElementsByTagName("Warehouse").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("Amount").item(0) != null)
		    	  pedido.setMonto(salesOrder.getElementsByTagName("Amount").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("ShippingCost").item(0) != null)
		    	  pedido.setCostoEnvio(salesOrder.getElementsByTagName("ShippingCost").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("PaidApproved").item(0) != null)
		    	  pedido.setPagoAprovado(salesOrder.getElementsByTagName("PaidApproved").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("PaymentStatus").item(0) != null)
		    	  pedido.setPagoEstatus(salesOrder.getElementsByTagName("PaymentStatus").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("DeliveryStatus").item(0) != null)
		    	  pedido.setEntregaEstatus(salesOrder.getElementsByTagName("DeliveryStatus").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("PaymentFulfillmentStatus").item(0) != null)
		    	  pedido.setCumplimientoPagoEstatus(salesOrder.getElementsByTagName("PaymentFulfillmentStatus").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("DeliveryFulfillmentStatus").item(0) != null)
		    	  pedido.setCumplimientoEntregaEstatus(salesOrder.getElementsByTagName("DeliveryFulfillmentStatus").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("DeliveryMethod").item(0) != null)
		    	  pedido.setEntregaMetodo(salesOrder.getElementsByTagName("DeliveryMethod").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("PaymentTerm").item(0) != null)
		    	  pedido.setPagoPlazo(salesOrder.getElementsByTagName("PaymentTerm").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("Currency").item(0) != null)
		    	  pedido.setMoneda(salesOrder.getElementsByTagName("Currency").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("IsOpen").item(0) != null)
		    	  pedido.setEstaAbierto(salesOrder.getElementsByTagName("IsOpen").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("IsCanceled").item(0) != null)
		    	  pedido.setEstaCancelado(salesOrder.getElementsByTagName("IsCanceled").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("HasAnyShipments").item(0) != null)
		    	  pedido.setTieneAlgunEnvio(salesOrder.getElementsByTagName("HasAnyShipments").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("Date").item(0) != null)
		    	  pedido.setFecha(salesOrder.getElementsByTagName("Date").item(0).getTextContent());
		      if(salesOrder.getElementsByTagName("Id").item(0) != null)
		    	  pedido.setIdIntegracionFactura(salesOrder.getElementsByTagName("Id").item(0).getTextContent());
		      
		      Element cliente = (Element) salesOrder.getElementsByTagName("Contact").item(0);
		      if(cliente.getElementsByTagName("Id").item(0) != null)
		    	  pedido.getCliente().setFolio(cliente.getElementsByTagName("Id").item(0).getTextContent());
		      if(cliente.getElementsByTagName("Name").item(0) != null)
		    	  pedido.getCliente().setNombre(cliente.getElementsByTagName("Name").item(0).getTextContent());
		      if(cliente.getElementsByTagName("ContactPerson").item(0) != null)
		    	  pedido.getCliente().setContacto(cliente.getElementsByTagName("ContactPerson").item(0).getTextContent());
		      if(cliente.getElementsByTagName("Mail").item(0) != null)
		    	  pedido.getCliente().setCorreo(cliente.getElementsByTagName("Mail").item(0).getTextContent());
		      if(cliente.getElementsByTagName("PhoneNumber").item(0) != null)
		    	  pedido.getCliente().setTelefono(cliente.getElementsByTagName("PhoneNumber").item(0).getTextContent());
		      if(cliente.getElementsByTagName("Type").item(0) != null)
		    	  pedido.getCliente().setTipo(cliente.getElementsByTagName("Type").item(0).getTextContent());
		      
		      Element perfil = (Element) cliente.getElementsByTagName("Profile").item(0);
		      pedido.getCliente().setPerfilId(perfil.getElementsByTagName("IntegrationId").item(0).getTextContent());
		      
		      Element domicilio = (Element) cliente.getElementsByTagName("Location").item(0);
		      if(domicilio.getElementsByTagName("StreetName").item(0) != null)
		    	  pedido.getCliente().setDomCalle(domicilio.getElementsByTagName("StreetName").item(0).getTextContent());
		      if(domicilio.getElementsByTagName("StreetNumber").item(0) != null)
		    	  pedido.getCliente().setDomNumero(domicilio.getElementsByTagName("StreetNumber").item(0).getTextContent());
		      if(domicilio.getElementsByTagName("AddressNotes").item(0) != null)
		    	  pedido.getCliente().setDomNotas(domicilio.getElementsByTagName("AddressNotes").item(0).getTextContent());
		      if(domicilio.getElementsByTagName("State").item(0) != null)
		    	  pedido.getCliente().setDomEstado(domicilio.getElementsByTagName("State").item(0).getTextContent());
		      if(domicilio.getElementsByTagName("City").item(0) != null)
		    	  pedido.getCliente().setDomCiudad(domicilio.getElementsByTagName("City").item(0).getTextContent());
		      if(domicilio.getElementsByTagName("Neighborhood").item(0) != null)
		    	  pedido.getCliente().setDomColonia(domicilio.getElementsByTagName("Neighborhood").item(0).getTextContent());
		      if(domicilio.getElementsByTagName("ZipCode").item(0) != null)
		    	  pedido.getCliente().setDomCP(domicilio.getElementsByTagName("ZipCode").item(0).getTextContent());
		      
		      NodeList pedidosML = salesOrder.getElementsByTagName("Integrations");
		      NodeList pagosML = salesOrder.getElementsByTagName("Payment");
		      NodeList productosML = salesOrder.getElementsByTagName("Lines");
		      
		      for(int i = 0; i < pedidosML.getLength(); i++) {
			      PedidoML pedidoML = new PedidoML();
			      
		    	  Node pedidoMLN = pedidosML.item(i);
		    	  Node productoMLN = productosML.item(i);
		    	  Node pagoMLN = pagosML.item(i);
			      Element pedidoMLE = (Element) pedidoMLN;
			      Element productoMLE = (Element) productoMLN;
			      Element pagoMLE = (Element) pagoMLN;
			      
			      if(pedidoMLE.getElementsByTagName("IntegrationId").item(0) != null)
			    	  pedidoML.setFolio(pedidoMLE.getElementsByTagName("IntegrationId").item(0).getTextContent());
			      if(productoMLE.getElementsByTagName("Price").item(0) != null)
			    	  pedidoML.getProducto().setMonto(Double.parseDouble(productoMLE.getElementsByTagName("Price").item(0).getTextContent()));
			      if(productoMLE.getElementsByTagName("Quantity").item(0) != null)
			    	  pedidoML.getProducto().setCantidad(Double.parseDouble(productoMLE.getElementsByTagName("Quantity").item(0).getTextContent()));
			      if(productoMLE.getElementsByTagName("Code").item(0) != null)
			    	  pedidoML.getProducto().setCodigo(productoMLE.getElementsByTagName("Code").item(0).getTextContent());
			      if(productoMLE.getElementsByTagName("Sku").item(0) != null)
			    	  pedidoML.getProducto().setSku(productoMLE.getElementsByTagName("Sku").item(0).getTextContent());
			      if(productoMLE.getElementsByTagName("Description").item(0) != null)
			    	  pedidoML.getProducto().setDescripcion(productoMLE.getElementsByTagName("Description").item(0).getTextContent());
			      
			      if(pagoMLE.getElementsByTagName("IntegrationId").item(0) != null)
			    	  pedidoML.getPago().setId(pagoMLE.getElementsByTagName("IntegrationId").item(0).getTextContent());
			      if(pagoMLE.getElementsByTagName("Date").item(0) != null)
			    	  pedidoML.getPago().setFecha(pagoMLE.getElementsByTagName("Date").item(0).getTextContent());
			      if(pagoMLE.getElementsByTagName("Amount").item(0) != null)
			    	  pedidoML.getPago().setMonto(pagoMLE.getElementsByTagName("Amount").item(0).getTextContent());
			      if(pagoMLE.getElementsByTagName("Status").item(0) != null)
			    	  pedidoML.getPago().setEstatus(pagoMLE.getElementsByTagName("Status").item(0).getTextContent());
			      if(pagoMLE.getElementsByTagName("Method").item(0) != null)
			    	  pedidoML.getPago().setMetodo(pagoMLE.getElementsByTagName("Method").item(0).getTextContent());
			      if(pagoMLE.getElementsByTagName("Notes").item(0) != null)
			    	  pedidoML.getPago().setNotas(pagoMLE.getElementsByTagName("Notes").item(0).getTextContent());
			      if(pagoMLE.getElementsByTagName("TransactionFee").item(0) != null)
			    	  pedidoML.getPago().setCuota(pagoMLE.getElementsByTagName("TransactionFee").item(0).getTextContent());
			      
			      pedido.getPedidos().add(pedidoML);

		      }
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Este es el puto error :" + e);
				logs.add(new Log(path.replace("\\\\192.168.1.34\\Privalia\\MercadoLibre\\Entrada\\", "").replace(".xml", ""),"Error", "Error en codificacion de archivo", new Date()));
			}
		
		for(int i = 0; i < pedido.getPedidos().size(); i++) {
			System.out.println(
					"Folio: " +  pedido.getPedidos().get(i).getFolio() + "\n" +
					"Monto: " +  pedido.getPedidos().get(i).getProducto().getMonto() + "\n" +
					"Cantidad: " +  pedido.getPedidos().get(i).getProducto().getCantidad() + "\n" +
					"Codigo: " +  pedido.getPedidos().get(i).getProducto().getCodigo() + "\n" +
					"Sku: " +  pedido.getPedidos().get(i).getProducto().getSku() + "\n" +
					"Descripcion: " +  pedido.getPedidos().get(i).getProducto().getDescripcion() + "\n" +
					"Folio de pago: " +  pedido.getPedidos().get(i).getPago().getId() + "\n" +
					"Fecha de Pago: " +  pedido.getPedidos().get(i).getPago().getFecha() + "\n" +
					"Monto de Pago: " +  pedido.getPedidos().get(i).getPago().getMonto() + "\n" +
					"Estatus de Pago: " +  pedido.getPedidos().get(i).getPago().getEstatus() + "\n" +
					"Metodo de Pago: " +  pedido.getPedidos().get(i).getPago().getMetodo() + "\n" +
					"Notas de Pago: " +  pedido.getPedidos().get(i).getPago().getNotas() + "\n" +
					"Cuota de Pago: " +  pedido.getPedidos().get(i).getPago().getCuota() + "\n"
			);
		}
		
		return pedido;
	}
	public List<Log> pedidoaSAP(List<Pedido> pedidos) {
		
		try {
			Calendar hoy = Calendar.getInstance();
			hoy.add(Calendar.DAY_OF_MONTH, 1);
			ConexionBD db = new ConexionBD();
			ConexionSap sap = new ConexionSap();
			System.out.println("Conectando con SAP...\n");
			sap.conectar();
			System.out.println("Conectado a SAP\n");
			for(int i = 0; i < pedidos.size(); i++) {
				for(int l = 0; l < pedidos.get(i).getPedidos().size(); l++) {
					String pedido = pedidos.get(i).getPedidos().get(l).getFolio();
					System.out.println("************************************************\nPedido:" + pedido);
					ResultSet resultado = db.consultar("select docEntry, CANCELED from ORDR where numAtCard = 'ML-"+pedido+"'");
					try {
						if(resultado.next()) {
							if(pedidos.get(i).getEstaCancelado().equals("true")){
								System.out.println("Pedido " + pedido + " cancelado\n");
								if(resultado.getString(2).equals("Y")) {
									System.out.println("Ya esta cancelado el pedido en SAP");
									logs.add(new Log(pedido,"Error", "Ya esta cancelado en SAP", new Date()));
								} else {
									db.consultar("select * from ODLN where numAtCard = 'ML-"+pedido+"'");
									if(db.resultado.next()){
										System.out.println("Pedido cancelado y con entrega " + pedido);
										logs.add(new Log(pedidos.get(i).getPedidos().get(0).getFolio(),"Error", "Pedido cancelado y con entrega " + pedido, new Date()));
									} else {
										IDocuments pedidoSAP = SBOCOMUtil.newDocuments(sap.company, SBOCOMConstants.BoObjectTypes_Document_oOrders);
										pedidoSAP.getByKey(resultado.getInt(1));
										System.out.println("Cancelando en SAP");
										if(pedidoSAP.cancel() != 0) {
											System.out.println("Hubo un error al intentar cancelar el documento en SAP: " + ((ICompany) pedidoSAP).getLastErrorDescription());
										} else {
											System.out.println("Se cancelo con exito");
											logs.add(new Log(pedido,"Error", "Pedido cancelado con exito", new Date()));
										}
									}
								}
								int k = 1;
								while(new File(carpetaML + "Procesados\\Duplicados\\"+pedidos.get(i).getPedidos().get(0).getFolio()+" cancelacion-" + k +".xml").exists()) {
									k++;
								}
								File file = new File(carpetaML + "Entrada\\"+pedidos.get(i).getPedidos().get(0).getFolio()+".xml");
								file.renameTo(new File(carpetaML + "Procesados\\Duplicados\\"+pedidos.get(i).getPedidos().get(0).getFolio()+" cancelacion-" + k +".xml"));
							} else {
								logs.add(new Log(pedido,"Error", "Ya existe este pedido", new Date()));
								int k = 1;
								while(new File(carpetaML + "Procesados\\Duplicados\\"+pedidos.get(i).getPedidos().get(0).getFolio()+"-" + k +".xml").exists()) {
									k++;
								}
								File file = new File(carpetaML + "Entrada\\"+pedidos.get(i).getPedidos().get(0).getFolio()+".xml");
								file.renameTo(new File(carpetaML + "Procesados\\Duplicados\\"+pedidos.get(i).getPedidos().get(0).getFolio()+"-" + k +".xml"));
							}
						} else if (pedidos.get(i).getAlmacen().equals("MercadoEnvios Full")) {
							
							int k = 1;
							
							while(new File(carpetaML + "Procesados\\Duplicados\\"+pedidos.get(i).getPedidos().get(0).getFolio()+"-" + k +".xml").exists()) {
								k++;
							}
							
							logs.add(new Log(pedido,"Correcto", "Pedido FullFilment", new Date()));
							
							File file = new File(carpetaML + "Entrada\\"+pedidos.get(i).getPedidos().get(0).getFolio()+".xml");
							file.renameTo(new File(carpetaML + "Procesados\\Duplicados\\"+pedidos.get(i).getPedidos().get(0).getFolio()+"-" + k +".xml"));
						} else {
							System.out.println("Creando el pedido " + pedido);
							IDocuments pedidoSAP = SBOCOMUtil.newDocuments(sap.company, SBOCOMConstants.BoObjectTypes_Document_oOrders);
							pedidoSAP.setDocDueDate(hoy.getTime());
							pedidoSAP.setCardCode("C-03826");
							pedidoSAP.setNumAtCard("ML-" + pedido);
							pedidoSAP.setComments("Produteka: " + pedidos.get(i).getFolio() + "\nPago: " + pedidos.get(i).getPedidos().get(l).getPago().getId());
							pedidoSAP.setSeries(74);
							if(pedidos.get(i).getGuia().length() >= 15) {
								pedidoSAP.getUserFields().getFields().item("U_NumdeGuia").setValue(pedidos.get(i).getGuia().substring(0, 15));
							}else {
								pedidoSAP.getUserFields().getFields().item("U_NumdeGuia").setValue(pedidos.get(i).getGuia());
							}
							System.out.println("sku "+pedidos.get(i).getPedidos().get(l).getProducto().getSku());
							pedidoSAP.getLines().setCostingCode5("1801");
							pedidoSAP.getLines().setCOGSCostingCode5("1801");
							pedidoSAP.getLines().setItemCode(pedidos.get(i).getPedidos().get(l).getProducto().getSku());
							pedidoSAP.getLines().setQuantity(pedidos.get(i).getPedidos().get(l).getProducto().getCantidad());
							pedidoSAP.getLines().setWarehouseCode("71");
							pedidoSAP.getLines().setTaxCode("IVAV16");
							pedidoSAP.getLines().setLineTotal(pedidos.get(i).getPedidos().get(l).getProducto().getMonto()/1.16);
							pedidoSAP.getLines().add();
							if(pedidoSAP.add() != 0) {
								System.out.println("No se pudo crear el pedido " + pedidos.get(i).getPedidos().get(0).getFolio() + " por: " + sap.company.getLastErrorDescription());
								File file = new File(carpetaML + "Procesados\\"+pedidos.get(i).getPedidos().get(0).getFolio()+".xml");
								file.renameTo(new File(carpetaML + "Entrada\\"+pedidos.get(i).getPedidos().get(0).getFolio() + ".xml"));
								logs.add(new Log(pedidos.get(i).getPedidos().get(0).getFolio(),"Error", sap.company.getLastErrorDescription(), new Date()));
							} else {
								pedidoSAP.getByKey(Integer.parseInt(sap.company.getNewObjectKey()));
								System.out.println("Pedido " + pedido + " creado con el id SAP " + pedidoSAP.getDocNum());
								logs.add(new Log(pedido,"Correcto", "Creado con el id SAP " + pedidoSAP.getDocNum(), new Date()));
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			System.out.println("Desconectando de SAP...");
			sap.company.disconnect();
			System.out.println("Desconectado de SAP");
		} catch (SBOCOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logs;
	}

	public static void main(String[] args) {
		Main main = new Main();
		List<Pedido> pedidos = new ArrayList<>();
		try {
			Files.walk(Paths.get(main.carpetaML + "Entrada")).forEach(ruta-> {
			    if (Files.isRegularFile(ruta)) {
			    	String archivo = ruta.toString();
			    	System.out.println(archivo);
			    	if(archivo.indexOf(".xml") != -1) {
			    		pedidos.add(main.xmlaPedido(archivo));
			    	}
			    }
			});
			System.out.println("Hay " + pedidos.size() + " archivos");
			if(pedidos.size() > 0){
				List<Log> logs = main.pedidoaSAP(pedidos);
				System.out.println("Pedido\tEstatus\tMensaje\tFecha");
				Mail mail = new Mail();
				mail.sendMail(logs);
				for(int i = 0; i < logs.size(); i++) {
					System.out.println(logs.get(i).getPedido() + "\t" + logs.get(i).getStatus() + "\t" + logs.get(i).getMensaje()+ "\t" + logs.get(i).getFecha());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
