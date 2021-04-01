
package com.nxygene.tutoring;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class RegisterActivity extends Activity {
	private final static java.text.SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat("yyyy/MM/dd");

	private EditText nameField;
	private EditText emailField;
	private EditText passwordField;
	private Button registerButton;

	private String name;
	private String email;
	private String password;

	private BackendlessUser user;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		initUI();
	}

	private void initUI() {
		nameField = (EditText) findViewById(R.id.nameField);
		emailField = (EditText) findViewById(R.id.emailField);
		passwordField = (EditText) findViewById(R.id.passwordField);
		registerButton = (Button) findViewById(R.id.registerButton);

		registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onRegisterButtonClicked();
			}
		});
	}

	private void onRegisterButtonClicked() {
		String nameText = nameField.getText().toString().trim();
		String emailText = emailField.getText().toString().trim();
		String passwordText = passwordField.getText().toString().trim();

		if (emailText.isEmpty()) {
			Toast.makeText(this, "Field 'email' cannot be empty.", Toast.LENGTH_SHORT).show();
			return;
		} else
			email = emailText;

		if (passwordText.isEmpty()) {
			Toast.makeText(this, "Field 'password' cannot be empty.", Toast.LENGTH_SHORT).show();
			return;
		}
		else
			password = passwordText;

		if (!nameText.isEmpty()) {
			name = nameText;
		}

		BackendlessUser user = new BackendlessUser();

		if (email != null) {
			user.setEmail(email);
		}

		if (password != null) {
			user.setPassword(password);
		}

		if (name != null) {
			user.setProperty("name", name);
		}


		Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
			@Override
			public void handleResponse(BackendlessUser response) {
				Resources resources = getResources();
				String message = String.format(resources.getString(R.string.registration_success_message), resources.getString(R.string.app_name));

				AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
				builder.setMessage(message).setTitle(R.string.registration_success);
				AlertDialog dialog = builder.create();
				dialog.show();
			}

			@Override
			public void handleFault(BackendlessFault fault) {
				AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
				builder.setMessage(fault.getMessage()).setTitle(R.string.registration_error);
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
	}
}

