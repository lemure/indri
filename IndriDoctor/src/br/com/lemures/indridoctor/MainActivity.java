package br.com.lemures.indridoctor;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.lemures.indridoctor.constants.Constantes;
import br.com.lemures.indridoctor.model.PacienteVO;

public class MainActivity extends Activity implements Runnable {

	final Context context = this;
	private Button btEsqueciSenha;  
	private Button btEnviar;
	private ImageButton btPerfilMedico;  
	private ProgressDialog dialog;
	private EditText txtLogin;
	private EditText txtSenha;
	private String toastText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btEsqueciSenha =  (Button) findViewById(R.id.bt_esqueci_senha);
		btPerfilMedico = (ImageButton) findViewById(R.id.bt_perfil_medico);
		btEnviar = (Button) findViewById(R.id.bt_enviar);
		txtLogin = (EditText) findViewById(R.id.txt_login);
		txtSenha = (EditText) findViewById(R.id.txt_senha);
		
		/**
		 * btEnviar Onclick
		 */
		btEnviar.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {

				try{
					dialog = ProgressDialog.show(MainActivity.this, "Login/Senha", "Aguardando Validação do Usuário", true);
					new Thread(MainActivity.this).start();
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}	
			}
		});
		
		
		/**
		 * btPerfilMedico Onclick
		 */
		btPerfilMedico.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View arg0) {
	 
				  Intent intent = new Intent(context, PerfilMedicoActivity.class);
				  startActivity(intent);
				  
			  }
		});
		
		
		/**
		 * btEsqueciSenha Onclick
		 */
		btEsqueciSenha.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View arg0) {
	 
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.activity_esqueci_senha);
				dialog.setTitle("Reenvio de Senha");
	 
				Button btCancelar = (Button) dialog.findViewById(R.id.bt_cancelar);
				Button btEnviar = (Button) dialog.findViewById(R.id.bt_enviar);
				
				btEnviar.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(context, "E-mail enviado com sucesso.", Toast.LENGTH_SHORT).show();							
						dialog.dismiss();
					}
				});
				
				btCancelar.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	 
				dialog.show();
			  }
			});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main2, menu);
		return true;
	}

	   @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }

	   
	   
	   /**
	    * Thread responsavel por iniciar a chamada do webservidce de validação do usuário
	    */
  	  @Override 
	   public void run() { 
		   doLoginWS();
	   }

	
	   private final String METHOD_NAME="fazerLogin";
	   private final String NAMESPACE ="http://vo.indridoctor.com.br";      
	   private final String SOAP_ACTION ="";
	   private final String URL = Constantes.IP_SERVER + "/IndriDoctorWS/services/PacienteWS";
	   
	   /**
	    * Chama o webservice que faz a validação do usuário
	    */
	   public PacienteVO doLoginWS() {
		   
		   
		SoapObject soap = new SoapObject(NAMESPACE, METHOD_NAME);
			soap.addProperty(Constantes.LOGIN, txtLogin.getText().toString());
			soap.addProperty(Constantes.SENHA, txtSenha.getText().toString());
			
		PacienteVO vo = null;
	    
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = soap;
			envelope.setOutputSoapObject(soap);
		
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		
		try { 

			httpTransport.call(SOAP_ACTION, envelope); 
			SoapObject responseWS = (SoapObject)envelope.getResponse();
			
			if(responseWS !=  null){
				vo = new PacienteVO();
				vo.setNome((responseWS.getProperty(Constantes.NOME)).toString());
				vo.setEmail((responseWS.getProperty(Constantes.EMAIL)).toString());
				vo.setLogin((responseWS.getProperty(Constantes.LOGIN)).toString());
				vo.setSenha((responseWS.getProperty(Constantes.SENHA)).toString());
				dialog.dismiss();
			}
			else{
				toastText = "Usuário ou senha incorretos.";
				dialog.dismiss();
				h.sendMessage(new Message());
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			dialog.dismiss();
			toastText = "Houve um problema ao tentar realizar o Login.";
			h.sendMessage(new Message());
		}

		return vo;

	   } 
	   
	  
	   /**
	    * Faz a chamada do Toast através de uma thread diferente da principal (no caso a do webservice)
	    */
	   Handler h = new Handler(){
		   @Override
		   public void handleMessage(Message msg){
			   Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();	
		   }
	   };
	   
	   
	   
}
