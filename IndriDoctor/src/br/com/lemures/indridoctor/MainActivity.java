package br.com.lemures.indridoctor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {

	final Context context = this;
	private Button btEsqueciSenha;  
	private ImageButton btPerfilMedico;  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btEsqueciSenha =  (Button) findViewById(R.id.bt_esqueci_senha);
		btPerfilMedico = (ImageButton) findViewById(R.id.bt_perfil_medico);
		
		/**
		 * 
		 */
		btPerfilMedico.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View arg0) {
	 
				  Intent intent = new Intent(context, PerfilMedicoActivity.class);
				  startActivity(intent);
				  
			  }
			});
		
		/**
		 * 
		 */
		btEsqueciSenha.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View arg0) {
	 
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.activity_esqueci_senha);
				dialog.setTitle("Reenvio de Senha");
	 
				Button dialogButton = (Button) dialog.findViewById(R.id.bt_cancelar);

				dialogButton.setOnClickListener(new OnClickListener() {
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

		// Inflate the menu; this adds items to the action bar if it is present.
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


}
