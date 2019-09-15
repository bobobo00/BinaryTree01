package TestTree;



import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * @ClassName BinaryTree
 * @Description 二叉树的相关操作
 * @Auther danni
 * @Date 2019/9/14 14:48]
 * @Version 1.0
 **/

public class BinaryTree {
    //先序遍历递归构建二叉树
    public static Node buildBirnaryTree01(Node root)  {
        Scanner scanner=new Scanner(System.in);
        char c=scanner.nextLine().charAt(0);
        if (c == '#') {
            root = null;
        } else {
            root = new Node();
            root.setVal(c);
            root.left=buildBirnaryTree01(root.left);//java中没有指针的思想，所以需要利用返回值将父节点和子节点的关系连接在一起。
            root.right=buildBirnaryTree01(root.right);
        }
        return root;
    }
    //先序遍历迭代构建二叉树
    public static Node buildBinaryTree02(Node root){
        Scanner scanner=new Scanner(System.in);
        Stack<Node> stack=new Stack<>();
        Node temp=root;
        System.out.println("请输入先序遍历二叉树序列");
        String str="abc##de#g##f###";
        char[] ch=str.toCharArray();
        int i=0;
        while(i<ch.length){
            if(ch[i]!='#'){
                if(root==null){
                    root=new Node();
                    root.setVal(ch[i]);
                    stack.push(root);
                    temp=root;
                }else {
                    temp.left=new Node(ch[i]);
                    temp=temp.left;
                    stack.push(temp);
                }

            }else{
                temp=stack.pop();
                if(ch[i+1]!='#'){
                    i++;
                    temp.right=new Node(ch[i]);
                    temp=temp.right;
                    stack.push(temp);
                }
            }
            i++;
            if(stack.empty()){
                break;
            }
        }
       return root;
    }
    //递归实现先序遍历二叉树
    public void preorder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root);
        preorder(root.left);
        preorder(root.right);
    }

    //递归实现中序遍历二叉树
    public void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.print(root);
        inorder(root.right);
    }
    //迭代实现后序遍历二叉树
    public void postorder(Node root){
        if(root==null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root);
    }
    //判断一颗树是否为另一颗树的子树
    public boolean HusSubtree(Node s,Node t){
        boolean result=false;
       if(s!=null&&t!=null){
           if(s.getVal()==t.getVal()){
               result=isSameTree(s,t);
           }
           if(!result){
               result=HusSubtree(s.left,t);
           }
           if(!result){
               result=HusSubtree(s.right,t);
           }
       }
       return result;
    }
    //返回树的最大深度
    public int getLength(Node root){
        if(root==null){
            return 0;
        }
        int left=getLength(root.left);
        int right=getLength(root.right);
        return Math.max(left+1,right+1);
    }
    //判断一颗树是否为平衡二叉树
    public boolean isBanlaced(Node root){
        if(root==null){
            return true;
        }
        if(!(isBanlaced(root.left))){
            return false;
        }
        if(!(isBanlaced(root.right))){
            return false;
        }
        int len=this.getLength(root.left)-this.getLength(root.right);
        if(Math.abs(len)<=1){
            return true;
        }
        return false;
    }
    //遍历思路求结点个数
    static int size=0;
    public  int getSize1(Node root){
        if(root==null){
            return 0;
        }
        size++;
       getSize1(root.left);
       getSize1(root.right);
        return size;
    }
    //子问题思路求结点个数
    public int getSize2(Node root){
        if(root==null){
            return 0;
        }
        int left=getSize2(root.left);
        int right=getSize2(root.right);
        return left+right+1;
    }
    //遍历思路求叶子结点个数
    static  int leafsize;
    public int getLeafsize1(Node root){
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            leafsize++;
        }
        getLeafsize1(root.left);
        getLeafsize1(root.right);
        return leafsize;
    }
    //子问题思路求叶子结点个数
    public int getLeafsize2(Node root){
        if(root==null){
            return 0;
        }
        if(root.right==null&&root.left==null){
            return 1;
        }
        return getLeafsize2(root.left)+getLeafsize2(root.right);
    }
    //子问题思路求第k层叶子结点个数
    public int getLevelsize(Node root,int k){
        if(root==null){
            return 0;
        }
        if(k==1){
            return 1;
        }
        return getLevelsize(root.left,k-1)+getLevelsize(root.right,k-1);
    }
    //递归遍历查找树中是否存在指定结点，存在返回该结点，否则返回null
   public Node findl(Node root,int val){
        if(root==null){
            return null;
        }
        if((Character)root.getVal()==val) {
            return root;
        }
        Node nodel=findl(root.left,val);
        if(nodel!=null){
            return nodel;
        }
        Node noder=findl(root.right,val);
        if(noder!=null){
            return noder;
        }
        return null;
    }
    //递归遍历查找树中是否存在指定结点，存在返回true，否则返回false
    public boolean find(Node root,int val){
        if(root==null){
            return false;
        }
        if((Character)root.getVal()==val){
            return false;
        }
        boolean b=find(root.left,val);
        if(b!=false){
            return true;
        }
        return find(root.right,val);
    }
    //判断两颗树是否互为镜像
    public boolean isMirrorTree(Node p,Node q){
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null){
            return true;
        }
        return p.getVal()==q.getVal()&&isMirrorTree(p.left,q.right)&& isMirrorTree(p.right,q.left);
    }
    //判断两颗数是否相同
    public boolean isSameTree(Node p,Node q){
        if(p==null&&q==null){
            return true;
        }
        if(q==null||q==null){
            return false;
        }
        return p.getVal()==q.getVal()&&isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
    public static void main(String[] args) {
        BinaryTree bt=new BinaryTree();
        Node root=null;
        /*root=bt.buildBirnaryTree01(root);
        System.out.println("先序遍历");
        bt.preorder(root);
        System.out.println();
        System.out.println("中序遍历");
        bt.inorder(root);
        System.out.println();
        System.out.println("后序遍历");
        bt.postorder(root);*/

        root=buildBinaryTree02(root);
        System.out.println(bt.getLength(root));
        size=0;
        System.out.println(bt.getSize1(root));
        System.out.println(bt.getSize2(root));
        leafsize=0;
        System.out.println(bt.getLeafsize1(root));
        System.out.println(bt.getLeafsize2(root));
        System.out.println(bt.getLevelsize(root,3));

        /*System.out.println("先序遍历");
        bt.preorder(root);
        System.out.println();
        System.out.println("中序遍历");
        bt.inorder(root);
        System.out.println();
        System.out.println("后序遍历");
        bt.postorder(root);*/

    }
}
