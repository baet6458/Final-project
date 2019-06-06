package android.example.finalproject;

public class Member {

    private int mBondNumber;
    private int machineNumber;
    private String mName;
    private String mEmail;



    public  Member(int bondNumber,String name, String email,int machineNumber){
        mBondNumber=bondNumber;
        mName=name;
        mEmail=email;
        this.machineNumber=machineNumber;
    }

    public int getBondNumber(){
        return mBondNumber;
    }

    public String getName(){
        return mName;
    }

    public String getmEmail(){
        return mEmail;
    }
    public int getMachineNumber(){
        return machineNumber;
    }

}
