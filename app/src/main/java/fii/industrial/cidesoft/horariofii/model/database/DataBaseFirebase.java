package fii.industrial.cidesoft.horariofii.model.database;

public class DataBaseFirebase {

    private static final DataBaseFirebase ourInstance = new DataBaseFirebase();

    public boolean isDummy() {
        return isDummy;
    }

    public void setDummy(boolean dummy) {
        isDummy = dummy;
    }

    private boolean isDummy = false;

    public static DataBaseFirebase getInstance() {
        return ourInstance;
    }

//    private FirebaseAuth mAuth;

    private DataBaseFirebase() {
//        mAuth = FirebaseAuth.getInstance();

    }

//    public FirebaseAuth getmAuth() {
//        return mAuth;
//    }

//    public void SignOut(){
//        mAuth.signOut();
//    }
//
//    public boolean isSignIn(){
//        return mAuth.getCurrentUser()!=null;
//    }




}
