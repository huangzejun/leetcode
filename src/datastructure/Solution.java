package datastructure;

public class Solution {
	public static void main(String[] args) {
		RandomListNode node1 = new RandomListNode(1);
		RandomListNode node2 = new RandomListNode(2);
		RandomListNode node3 = new RandomListNode(3);
		RandomListNode node4 = new RandomListNode(4);
		RandomListNode node5 = new RandomListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node1.random = node3;
		node2.random = node5;
		node4.random = node2;
		Solution solution = new Solution();
		RandomListNode res = solution.Clone(node1);
		while(res!=null){
			System.out.println(res.label + "," + res.random.label);
			res = res.next;
		}
	}
    public RandomListNode Clone(RandomListNode pHead)
    {
        RandomListNode node = pHead;
        RandomListNode res = null;
        RandomListNode temp = null;
        while(node!=null){
            RandomListNode temp1 = new RandomListNode(node.label);
            temp1.next = node.next;
            node.next = temp1;
            node = temp1.next;
        }
        node = pHead;
        while(node!=null){
            if(node.random!=null){
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }
        res = pHead.next;
        node = pHead;
        while(node!=null){
            temp = node.next;
            node.next = temp.next;
            if(node.next!=null){
            	temp.next = node.next.next;
            }
            node = node.next;
            temp = temp.next;
        }
        return res;
    }
}
