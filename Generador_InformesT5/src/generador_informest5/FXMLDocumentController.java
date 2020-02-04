/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generador_informest5;

import static java.lang.System.exit;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alejandro
 */
public class FXMLDocumentController implements Initializable
{
    
    @FXML
    private Menu informes;
    @FXML
    private MenuItem listadoFacturas;
    @FXML
    private MenuItem ventasTotales;
    @FXML
    private MenuItem facturasCliente;
    @FXML
    private MenuItem subinforme;

    
    private Connection conexion = null;
    @FXML
    private TextField campo;
    private Label etiqueta;
    
    private void handleButtonAction(ActionEvent event)
    {
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        
        String baseDatos = "jdbc:hsqldb:hsql://localhost:9001/xdb";
        String usuario = "sa";
        String clave = "";
        try
        {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conexion = DriverManager.getConnection(baseDatos, usuario, clave);
        } catch (ClassNotFoundException cnfe)
        {
            System.err.println("Fallo al cargar JDBC");
            System.exit(1);
        } catch (java.lang.InstantiationException sqlex)
        {
            System.err.println("Imposible Conectar");
            System.exit(1);
        } catch (Exception ex)
        {
            System.err.println("Imposible Conectar");
            System.exit(1);
        }

    }    

    @FXML
    private void listarFacturas(ActionEvent event) 
    {           
            
        try
        {
            JasperReport jr = (JasperReport) JRLoader.loadObject(Generador_InformesT5.class.getResource("EJ4.10_FACTURAS.jasper"));
                        
            
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, conexion);
            JasperViewer jv = new JasperViewer(jp);
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
          
            jv.setZoomRatio((float) 1.25);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.requestFocus();
            jv.setVisible(true);
        } catch (JRException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ventasTotales(ActionEvent event)
    {
        
         try
        {
            JasperReport jr = (JasperReport) JRLoader.loadObject(Generador_InformesT5.class.getResource("EJ4.10_VENTAS_TOTALES.jasper"));
           
            
            
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, conexion); 
            JasperViewer jv = new JasperViewer(jp);
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
           
            jv.setZoomRatio((float) 1.25);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.requestFocus();
            jv.setVisible(true);
        } catch (JRException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void facturasCliente(ActionEvent event)
    {
        
        try
                {
                    int codigo = 0;
                    codigo = (Integer.valueOf(campo.getText())); 
                    JasperReport jr = (JasperReport) JRLoader.loadObject(Generador_InformesT5.class.getResource("EJ4.10_FACTURAS_XCLIENTE.jasper"));
                    Map parametros = new HashMap<String, Object>();
                    parametros.put("idCliente", codigo);

                    JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros , conexion); //Rellena parametros
                    JasperViewer jv = new JasperViewer(jp);
                    jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);

                    jv.setZoomRatio((float) 1.25);
                    jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                    jv.requestFocus();
                    jv.setVisible(true);
                } catch (JRException ex)
                {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }        

    }
    


   @FXML
    private void subinforme(ActionEvent event)
    {
          try
        {
           
            JasperReport jr = (JasperReport) JRLoader.loadObject(Generador_InformesT5.class.getResource("EJ4.10_INFORME_PRINCIPAL.jasper"));                 
          
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null , conexion);
            JasperViewer jv = new JasperViewer(jp);
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
           
            jv.setZoomRatio((float) 1.25);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.requestFocus();
            jv.setVisible(true);
        } catch (JRException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }

    private void salir(ActionEvent event)
    {        
        exit(0);
    }

    
  

    
    
   

}


