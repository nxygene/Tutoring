
package com.nxygene.tutoring;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.backendless.Backendless;

public class RestorePasswordActivity extends Activity {
	private Button restorePasswordButton;
	private EditText loginField;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restore_password);

		initUI();
	}

	private void initUI() {
		restorePasswordButton = (Button) findViewById(R.id.restorePasswordButton);
		loginField = (EditText) findViewById(R.id.loginField);

		restorePasswordButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onRestorePasswordButtonClicked();
			}
		});
	}

	public void onRestorePasswordButtonClicked() {
		String login = loginField.getText().toString();
		Backendless.UserService.restorePassword(login, new DefaultCallback<Void>( this) {
			@Override
			public void handleResponse(Void response) {
				super.handleResponse(response);

				AlertDialog.Builder builder = new AlertDialog.Builder( RestorePasswordActivity.this);
				builder.setMessage(R.string.password_recovered_message).setTitle(R.string.password_recovery_page_name);
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
	}
}

