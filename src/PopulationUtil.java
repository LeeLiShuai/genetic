import com.sun.org.apache.bcel.internal.generic.POP;

import java.io.*;
import java.util.*;

/**
 * 种群的工具类
 */
public class PopulationUtil {

    /**
     * 计算每个个体的适应度
     * @param population
     * @return
     */
    public static void setEveryOneFitness(Population population) {
        List<Chromosome> chromosomeList = population.getChromosomeList();
        chromosomeList.forEach(e->{
            e.setFitness(ChromosomeUtil.setFitness(population,e));
        });
        chromosomeList.stream().sorted(Comparator.comparingDouble(Chromosome::getFitness));
        chromosomeList.forEach(e->{
            e.setFitness(e.getFitness()-chromosomeList.get(0).getFitness()+1);
        });
        population.setTotalFitness(chromosomeList.stream().mapToDouble(Chromosome::getFitness).sum());
    }

    /**
     * 获取适应度最高的个体
     *
     * @param population
     * @return
     */
    public static Chromosome getBestChromose(Population population) {
        Optional<Chromosome> chromosome = population.getChromosomeList().stream().max(Comparator.comparingDouble(Chromosome::getFitness ));
        return chromosome.get();
    }

    /**
     * 种群交叉，产生下一代
     *
     * @param population
     * @return
     */
    public static Population cross(Population population) throws IOException, ClassNotFoundException {
        Random random = new Random();
        Population newPopulation = new Population();
        newPopulation.setTarget(population.getTarget());
        newPopulation.setCrossRate(population.getCrossRate());
        newPopulation.setMutationRate(population.getMutationRate());
        newPopulation.setPopulationSize(population.getPopulationSize());
        newPopulation.setChromosomeList(deepCopy(population.getChromosomeList()));
        for(int i = 0;i<population.getChromosomeList().size();i++){
            Chromosome father = getOneByRate(population);
            Chromosome mother = getOneByRate(population);
            double crossRate = random.nextDouble();
            if(crossRate <= population.getCrossRate()){
                newPopulation.getChromosomeList().set(i, ChromosomeUtil.cross(father,mother));
            }else{
                newPopulation.getChromosomeList().set(i,father.getFitness()>mother.getFitness()? father.clone():mother.clone());
            }
        }
        return newPopulation;
    }

    /**
     * 种群变异
     *
     * @param population
     * @return
     */
    public static void mutation(Population population) {
        Random random = new Random();
        for(int i =0;i<population.getChromosomeList().size();i++){
            double mutationRate = random.nextDouble();
            if(mutationRate <= population.getMutationRate()){
                ChromosomeUtil.mutation(population.getChromosomeList().get(i));
            }
        }
    }

    /**
     * 种群进化
     * @param population
     * @return
     */
    public static Population evolution(Population population) throws IOException, ClassNotFoundException {
        //交叉
        population = cross(population);
        //变异
        mutation(population);
        return population;
    }

    public static Chromosome getOneByRate(Population population){
        Random random = new Random();
        int index = random.nextInt(population.getTotalFitness().intValue());
        int sum = 0;
        for(int i = 0;i<population.getChromosomeList().size();i++){
//            System.out.println(i);
//            System.out.println(population.getChromosomeList().get(i));
//            System.out.println(population.getChromosomeList().get(i).getFitness());
            sum+=population.getChromosomeList().get(i).getFitness();
            if(index < sum){
                return population.getChromosomeList().get(i).clone();
            }
        }
        return  population.getChromosomeList().get(population.getChromosomeList().size()-1).clone();
    }

    public static List deepCopy(List src) throws IOException, ClassNotFoundException{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in =new ObjectInputStream(byteIn);
        List dest = (List)in.readObject();
        return dest;
    }
}
