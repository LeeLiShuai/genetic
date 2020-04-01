import java.util.ArrayList;
import java.util.List;

/**
 * 种群，进化的单位，由若干个染色体组成
 * 为了方便表示，属性设为public
 */
public class Population {
    /**
     * 种群大小，表示一个种群中有多少个个体（染色体）
     */
    private int populationSize = 100;

    /**
     * 交叉几率，种群中产生下一代时
     */
    private double crossRate = 0.8;

    /**
     * 变异几率
     */
    private double mutationRate =0.05;

    /**
     * 目标，进化的最终目标
     */
    private String target;

    private List<Chromosome> chromosomeList = new ArrayList<>();

    private Double totalFitness;

    public Population() {
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public double getCrossRate() {
        return crossRate;
    }

    public void setCrossRate(double crossRate) {
        this.crossRate = crossRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Chromosome> getChromosomeList() {
        return chromosomeList;
    }

    public void setChromosomeList(List<Chromosome> chromosomeList) {
        this.chromosomeList = chromosomeList;
    }

    public Double getTotalFitness() {
        return totalFitness;
    }

    public void setTotalFitness(double totalFitness) {
        this.totalFitness = totalFitness;
    }
}
