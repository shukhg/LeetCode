# python 中的 List数组有 sort() 方法可以对 List 进行排序
# 不能像 Java 这样往 Set 中 add List类型的数据
# 可以一次往 List 中直接 append 一个 List

class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        if (nums is None) or (len(nums) <= 2):
            return []
        nums.sort()
        result = []
        for i in range(len(nums)):
            if (i != 0) and (nums[i] == nums[i - 1]):   # 此处保证不出现重复
                continue
            j = i + 1
            k = len(nums) - 1
            while j < k:
                if nums[i] + nums[j] + nums[k] == 0:
                    result.append([nums[i],nums[j],nums[k]])   # 一次性直接 append 进去
                    while (j < k) and (nums[j] == nums[j + 1]):
                        j = j + 1
                    while (j < k) and (nums[k] == nums[k - 1]):
                        k = k - 1
                    j = j + 1
                    k = k - 1
                elif nums[i] + nums[j] + nums[k] < 0:
                    j = j + 1
                else:
                    k = k - 1
        return result
                