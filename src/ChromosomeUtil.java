import java.security.SecureRandomSpi;
import java.util.Random;

/**
 * 染色体工具类
 */
public class ChromosomeUtil {

    /**
     * 随机生成一个个体
     * @return
     */
    public static Chromosome randomOne(int length){
        Chromosome chromosome = new Chromosome();
        chromosome.setStr(randomStr(length));
        return chromosome;
    }

    public static double setFitness(Population population,Chromosome chromosome){
        String target = population.getTarget();
        String str = chromosome.getStr();
        int result = 0;
        for(int i=0;i<target.length();i++){
            if(target.charAt(i) == str.charAt(i)){
                result++;
            }
        }
        return result;
    }

    /**
     * 随机n位的可见字符串
     * @param length
     * @return
     */
    public static String randomStr(int length){
        Random random = new Random();
        char c;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            c = (char)(random.nextInt(95)+26);
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 交叉产生下一代
     * @param father
     * @param mother
     * @return
     */
    public static Chromosome cross(Chromosome father, Chromosome mother) {
        Chromosome child = new Chromosome();
        char c;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<father.getStr().length();i++){
            if(i%2 == 0){
                c = father.getStr().charAt(i);
            }else {
                c = mother.getStr().charAt(i);
            }
            sb.append(c);
        }
        child.setStr(sb.toString());
        return child;
    }

    /**
     * 个体变异
     * @param chromosome
     */
    public static void mutation(Chromosome chromosome) {
        Random random = new Random();
        int[] indexs = new int[5];
        StringBuilder sb = new StringBuilder(chromosome.getStr());
        for(int i=0;i< indexs.length;i++){
            indexs[i]=random.nextInt(chromosome.getStr().length());
            sb.setCharAt(indexs[i], (char)(random.nextInt(95)+26));
        }
        chromosome.setStr(sb.toString());
    }
}
