package com.example.lab2.PojoClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Civilization   //POJO class для загрузки данных из json
{

    private final String URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";

    @SerializedName("graphic")
    @Expose
    private String graphicUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("helptext")
    @Expose
    private String helptext;


    public String getHelpText()
    {
        return helptext;
    }

    public String getGraphic()
    {
        return URL + graphicUrl;
    }

    public void setGraphic(String graphic)
    {
        this.graphicUrl = graphic;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getHelptext()
    {
        return helptext;
    }

    public void setHelptext(String helptext)
    {
        this.helptext = helptext;
    }
}