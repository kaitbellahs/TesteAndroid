package br.com.tialtonivel.testandroid.testanroidjava.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

public class Fund {
    public Screen screen;

    public String toString() {
        return String.format("title:%s,fundName:%s",
                screen.title, screen.fundName);
    }

    public static class Screen {
        public String title;
        public String fundName;
        public String whatIs;
        public String definition;
        public String riskTitle;
        public int risk;
        public String infoTitle;
        public MoreInfo moreInfo;
        public List<Info> info;
        public List<Info> downInfo;

        public static class Info {
            public String name;
            public String data;

            public Info() {

            }

            public Info(@JsonProperty("name") String name, @JsonProperty("data") String data) {
                this.name = name;
                this.data = data;
            }
        }

        public static class MoreInfo {
            public Inv month;
            public Inv year;

            @JsonProperty("12months")
            public Inv months12;

            /*public MoreInfo(@JsonProperty("month") String month, @JsonProperty("year") String year, @JsonProperty("12months") String months12) {
                this.name=name;
                this.data=data;
            }*/
            public class Inv {
                public double fund;
                public double CDI;
            }
        }


        public String[] getTexts() {

            return new String[]{
                    title,
                    fundName,
                    whatIs,
                    definition,
                    riskTitle
            };
        }
    }

}