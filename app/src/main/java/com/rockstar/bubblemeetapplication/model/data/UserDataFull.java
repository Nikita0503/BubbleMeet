
package com.rockstar.bubblemeetapplication.model.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataFull {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("Smoking")
    @Expose
    public int smoking;
    @SerializedName("Marred")
    @Expose
    public int marred;
    @SerializedName("Children")
    @Expose
    public int children;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Last_name")
    @Expose
    public String lastName;
    @SerializedName("Photo")
    @Expose
    public List<String> photo = null;
    @SerializedName("City")
    @Expose
    public String city;
    @SerializedName("AvatarSmall")
    @Expose
    public String avatarSmall;
    @SerializedName("AvatarFull")
    @Expose
    public String avatarFull;
    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("EyeColor")
    @Expose
    public String eyeColor;
    @SerializedName("Login")
    @Expose
    public String login;
    @SerializedName("Gender")
    @Expose
    public String gender;
    @SerializedName("Age")
    @Expose
    public String age;
    @SerializedName("Location")
    @Expose
    public String location;
    @SerializedName("Height")
    @Expose
    public String height;
    @SerializedName("Religion")
    @Expose
    public String religion;
    @SerializedName("Looking")
    @Expose
    public String looking;
    @SerializedName("Hobbes")
    @Expose
    public String hobbes;
    @SerializedName("timeOnline")
    @Expose
    public String timeOnline;

}
