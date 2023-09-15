//setup class
public class Planters {
    
    public Planters() {
    }

    //put everything into a new array while sorting it, then loop through the array and remove the 2 largest numbers unless the 2 largest numbers are equal and then return false;
    public boolean replantable(int p, int[] r, int[] sizes){
        int[] plantable = new int[p+r.length - 1];
        for(int i = 0; i < p - 1; i++){
            if(sizes[i] > sizes[i+1]){
                plantable[i] = sizes[i];
        }
        for(int i = 0; i < r.length; i++){
            plantable[i+p] = r[i];
        }
        return true;
    }
}