package com.mglj.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface find_dust_Interface {

    @GET("/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst")
    Call<Find_dust_Repo> get_weather(@Query(value = "serviceKey",encoded = true) String serviceKey, @Query("numOfRows") int numOfRows, @Query("pageNo") int pageNo,
                           @Query("itemCode") String itemCode, @Query("dataGubun") String dataGubun, @Query("searchCondition") String searchCondition,
                           @Query("_returnType") String _returnType);


}
