public class RecommendationEngine {
    public String[] getUserHistorygerne(){//这个方法用于接收用户观看过的电影种类

        return ;
    }
    public Movie[] Historyviewed(){}//这个方法用来接收用户的观看记录（把电影名字用编号来储存起来）

    public Movie[] Watchlist(){}//这个方法用来接收Watchlist

    public Movie[] allMovie(){

    }//这个方法用来接收所有的movie

    public int[] getEachnum(){//这个方法记录用户看过的电影中每个电影种类的次数
        String[] historygerne= getUserHistorygerne();//接收第一个方法的返回值。

        int Dramanum=0;//初始化数值，用于在遍历中存储每个电影种类观看过的次数
        int Crimenum=0;
        int Actionnum=0;
        int Fantasynum=0;
        int Sci_Finum=0;
        int Thrillernum=0;
        int Animationnum=0;
        int Warnum=0;
        int Mysterynum=0;
        int Horrornum=0;
        int Comedynum=0;
        int Romancenum=0;
        int Musicaknum=0;
        int Sportnum=0;
        int Biographynum=0;
       for (int i=0 ;i<historygerne.length;i++){
           String gerne=historygerne[i];//遍历一下数组，开始记录
           if (gerne.contains(" Crime")){ Crimenum++;}//为了方便记录，每个种类按照index进行一波编号，Crime是种类0
           if (gerne.contains("Action")){Actionnum++;}//1
           if (gerne.contains("Fantasy")){Fantasynum++;}//2
           if (gerne.contains("Sci_Fi")){Sci_Finum++;}//3
           if (gerne.contains("Drama")){Dramanum++;}//4
           if (gerne.contains("Thriller")){Thrillernum++;}//5
           if (gerne.contains("Animation")){Animationnum++;}//6
           if (gerne.contains("War")){Warnum++;}//7
           if (gerne.contains("Mystery")){Mysterynum++;}//8
           if (gerne.contains("Horror")){Horrornum++;}//9
           if (gerne.contains("Comedy")){Comedynum++;}//10
           if (gerne.contains("Romance")){Romancenum++;}//11
           if (gerne.contains("Musicak")){Musicaknum++;}//12
           if (gerne.contains("Sport")){Sportnum++;}//13
           if (gerne.contains("Biography")){Biographynum++;}//14
       }

              int[] countnum= {Crimenum,Actionnum,Fantasynum,Sci_Finum,Dramanum,Thrillernum,Animationnum,Warnum,Mysterynum,Horrornum,Comedynum,Romancenum,Musicaknum,Sportnum,Biographynum};
        return countnum;


    }

    public double[] findmovienum(){ //该方法用于找到每个电影应该寻找的（最大）次数
        int sum=0;
        int[] num=getEachnum();
        double[]doublenum=new double[num.length];//把整数数组转化成小数数组，方便后续计算
        for (int i=0;i<num.length;i++){
            doublenum[i]=num[i];

        }
        for (int i=0;i<num.length;i++){//得到和
            sum=sum+num[i];

        }
        for(int i=0;i<num.length;i++){
            doublenum[i]=Math.floor(doublenum[i]/sum);//得到每个电影应该推荐的个数

        }
        double doublesum=0;
        for(int i=0;i<num.length;i++){
            doublesum=doublenum[i]+doublesum;
        }
        int secondsum=(int) doublesum;
        int sub=sum-secondsum;
        int max=0;
        int maxindex=0;
        for(int i=0;i<num.length;i++){
            max=Math.max(max,num[i]);
        }
        for(int i=0;i<num.length;i++){//找到观看次数最多的电影类型
            if(num[i]==max){maxindex=i;}
        }
        doublenum [maxindex]+=sub;//把差的都补给用户最喜欢看的电影
        return doublenum;
    }
    public Movie[] TOGhisANDview(){//面相对象，把history 与 view合并起来


    }

    public Movie[] Rank0( ){//这个方法用于给相同种类的电影储存在同一个Array中，并且进行排序。
        int TotalKindnum=0;//这个变量用于计算每个种类的数量




    }

    public String[] Final10(){
        String[] Final10=new String[10];
        String[][] Rank=Rank();
        for

    }


    public String[] Top10Movies(){//最终输出十个电影名字







    }
}


}
