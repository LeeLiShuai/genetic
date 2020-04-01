import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Population population = new Population();
        //进化终极目标
        population.setTarget(loadFile());
        //初始化种群
        for(int i =0;i<population.getPopulationSize();i++){
            population.getChromosomeList().add(ChromosomeUtil.randomOne(population.getTarget().length()));
        }
        //计算适应度
        PopulationUtil.setEveryOneFitness(population);
        int i = 0;
        System.out.println("No."+i+",bestFitness:  "+PopulationUtil.getBestChromose(population).getFitness());
        //种群中的最优个体的适应度没有达到要求（当适应度等于目标的长度时，个体的str与目标相同）
        while(PopulationUtil.getBestChromose(population).getFitness() < population.getTarget().length()-31){
            population =  PopulationUtil.evolution(population);
            PopulationUtil.setEveryOneFitness(population);
            System.out.println("No."+i+",bestFitness:  "+PopulationUtil.getBestChromose(population).getFitness());
            if(PopulationUtil.getBestChromose(population).getFitness().intValue()%100==0){
                System.out.println(PopulationUtil.getBestChromose(population).getStr());
            }
            i++;
        }
        System.out.println(PopulationUtil.getBestChromose(population).getStr());
    }



    public static String loadFile() throws IOException {
        File file = new File("source/a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuffer sb = new StringBuffer();
        String text = null;
        while((text = bufferedReader.readLine()) != null){
            sb.append(text);
        }
        return sb.toString();
    }
}
