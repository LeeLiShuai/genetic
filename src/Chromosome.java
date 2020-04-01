import java.io.Serializable;

/**
 * 染色体，表示种群中的个体
 */
public class Chromosome implements Cloneable,Serializable {
    /**
     * 个体的适应度，表示个体基因的优劣，越大越好
     */
    private Double fitness;

    /**
     * 个体基因表达出的性状，在这个实例中，没有编码解码的过程，基因就是个体的性状，一串字符串
     */
    private String str;

    public Chromosome() {
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }


    public Chromosome clone() {
        Chromosome o = null;
        try {
            o = (Chromosome) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}