package deyse.souza.appvacina.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static FirebaseAuth auth;
    private static DatabaseReference firebase;

    public static DatabaseReference getFirebaseDatabase(){
        if(firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }

    public static FirebaseAuth getFirebaseAutenticacao() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
