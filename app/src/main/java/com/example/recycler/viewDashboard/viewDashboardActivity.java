package com.example.recycler.viewDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycler.R;
import com.example.recycler.alerts.ShowAlert;
import com.example.recycler.dialogues.ShowDialog;
import com.example.recycler.model.userModel;
import com.example.recycler.presenter.dashboardPresenter.presenterDashboard;
import com.example.recycler.presenter.dialogPresenter.presenterDialog;
import com.example.recycler.utilities.ConstantsUtility;
import com.example.recycler.utilities.PreferenceM;
import com.example.recycler.viewDashboard.viewCollectionPoint.viewCollectionPointActivity;
import com.example.recycler.viewDashboard.viewEvents.viewEventsActivity;
import com.example.recycler.viewDashboard.viewMaterials.viewMaterialsActivity;
import com.example.recycler.viewDashboard.viewPoints.viewPointsActivity;
import com.example.recycler.viewDashboard.viewPurchase.viewPurchaseActivity;
import com.example.recycler.viewDashboard.viewRecycler.viewRecyclerActivity;
import com.example.recycler.viewDashboard.viewRedeemPoints.viewRedeemPointsActivity;
import com.example.recycler.viewLogin.viewLogInActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.Normalizer;
import java.util.Objects;

public class viewDashboardActivity extends AppCompatActivity implements DashboardContract.attachViewUser {

    private presenterDialog mPresenterDialog;
    private Dialog dialog;
    private PreferenceM mPreferenceM;
    private presenterDashboard presenterDashboard;
    private ShowDialog showDialog;
    private ShowAlert showAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dashboard);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        presenterDashboard = new presenterDashboard(db);
        presenterDashboard.attachView(this);

        dialog = new Dialog(this);

        mPresenterDialog = new presenterDialog(this, dialog);
        mPreferenceM = new PreferenceM(this);

        findViewById(R.id.button_recycle).setOnClickListener(v -> startActivity(new Intent(this, viewRecyclerActivity.class)));
        findViewById(R.id.button_accumulated).setOnClickListener(v -> startActivity(new Intent(this, viewPointsActivity.class)));
        findViewById(R.id.button_materials).setOnClickListener(v -> startActivity(new Intent(this, viewMaterialsActivity.class)));
        findViewById(R.id.button_crafts).setOnClickListener(v -> startActivity(new Intent(this, viewPurchaseActivity.class)));
        findViewById(R.id.button_events).setOnClickListener(v -> startActivity(new Intent(this, viewEventsActivity.class)));
        findViewById(R.id.button_collection_points).setOnClickListener(v -> startActivity(new Intent(this, viewCollectionPointActivity.class)));
        findViewById(R.id.button_redeem_point).setOnClickListener(v -> startActivity(new Intent(this, viewRedeemPointsActivity.class)));

        showDialog = new ShowDialog(this);
        showAlert = new ShowAlert(this);
        ImageView logout_app = findViewById(R.id.logout_app);
        logout_app.setOnClickListener(v -> logoutApp());
        get_data();

    }

    private void logoutApp() {
        mPresenterDialog.initDialogLogout();
        dialog.show();
        TextView text_logout = dialog.findViewById(R.id.text_logout);
        ImageView close_dial = dialog.findViewById(R.id.close_dial);
        TextInputLayout names_logout = dialog.findViewById(R.id.names_logout);
        TextInputLayout surnames_logout = dialog.findViewById(R.id.surnames_logout);
        TextInputLayout email_logout = dialog.findViewById(R.id.email_logout);

        close_dial.setOnClickListener(v -> dialog.dismiss());

        Objects.requireNonNull(names_logout.getEditText()).setText(mPreferenceM.getString(ConstantsUtility.KEY_FULL_NAME_USER));
        Objects.requireNonNull(surnames_logout.getEditText()).setText(mPreferenceM.getString(ConstantsUtility.KEY_LAST_NAME_USER));
        Objects.requireNonNull(email_logout.getEditText()).setText(mPreferenceM.getString(ConstantsUtility.KEY_EMAIL_USER));

        text_logout.setOnClickListener(v -> {
            mPreferenceM.clearPreference();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, viewLogInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    private void get_data() {

        String CELLULAR = mPreferenceM.getString(ConstantsUtility.KEY_CELLULAR_USER);
        if (CELLULAR == null) {
            String uid = mPreferenceM.getString(ConstantsUtility.KEY_ID_USER);
            if (uid == null) {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                assert currentFirebaseUser != null;
                presenterDashboard.get_data(currentFirebaseUser.getUid());
            } else {
                presenterDashboard.get_data(uid);
            }
        }
    }

    @Override
    public void set_data_user(userModel model) {

        mPreferenceM.putString(ConstantsUtility.KEY_FULL_NAME_USER, model.getFullName());
        mPreferenceM.putString(ConstantsUtility.KEY_LAST_NAME_USER, model.getLastName());
        mPreferenceM.putString(ConstantsUtility.KEY_EMAIL_USER, model.getEmail());
        mPreferenceM.putString(ConstantsUtility.KEY_CELLULAR_USER, model.getCellular());

        String name = model.getFullName();
        String last = model.getLastName();
        String email = model.getEmail();
        String emailS = email.substring(0, 3);
        String nameR = name.replace(" ", "");
        String lastR = last.replace(" ", "");
        String KEY_USER = lastR + emailS + nameR;

        String chainNormalize = Normalizer.normalize(KEY_USER, Normalizer.Form.NFD);
        String chain_without_accents = chainNormalize.replaceAll("[^\\p{ASCII}]", "");

        mPreferenceM.putString(ConstantsUtility.KEY_USER, chain_without_accents);
    }

    @Override
    public void showProgress() {
        showDialog.LoadingData();
    }

    @Override
    public void dismissProgress() {
        showDialog.DialogDismiss();
    }

    @Override
    public void errorMessage(String message) {
        showAlert.AnErrorOccurred(message);
    }
}