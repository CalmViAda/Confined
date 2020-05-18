package com.example.confined.presentation.model;

public class GlobalData {

    private Integer NewConfirmed;
    private Integer TotalConfirmed;
    private Integer NewDeaths;
    private Integer TotalDeaths;
    private Integer NewRecovered;
    private Integer TotalRecovered;

    public GlobalData(Integer NewConfirmed, Integer TotalConfirmed,Integer NewDeaths,
                      Integer TotalDeaths, Integer NewRecovered,Integer TotalRecovered){

        this.NewConfirmed=NewConfirmed;
        this.TotalConfirmed=TotalConfirmed;
        this.NewDeaths=NewDeaths;
        this.TotalDeaths=TotalDeaths;
        this.NewRecovered=NewRecovered;
        this.TotalRecovered=TotalRecovered;

    }

    public Integer getNewConfirmed() {
        return NewConfirmed;
    }

    public Integer getTotalConfirmed() {
        return TotalConfirmed;
    }

    public Integer getNewDeaths() {
        return NewDeaths;
    }

    public Integer getTotalDeaths() {
        return TotalDeaths;
    }

    public Integer getNewRecovered() {
        return NewRecovered;
    }

    public Integer getTotalRecovered() {
        return TotalRecovered;
    }
}
