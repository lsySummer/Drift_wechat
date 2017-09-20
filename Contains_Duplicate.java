class Solution {
    public boolean containsDuplicate(int[] nums) {
        if(nums.length == 0 || nums.length == 1){return false;}
        Arrays.sort(nums);
        int j = nums[0];
        for(int i = 1; i < nums.length; i ++){
            if(nums[i] != j){
                j = nums[i];
            }else{
                return true;
            }
        }
        return false;
    }
}