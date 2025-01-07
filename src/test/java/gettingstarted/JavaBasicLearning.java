package gettingstarted;

import com.github.javafaker.Faker;

import java.util.*;

public class JavaBasicLearning {


  public static void main(String[] args) {
    // only work for command - java xx.java -version
    for (String arg : args) {
      if ("-version".equals(arg)) {
        System.out.println("v 1.0");
        break;
      }
    }
    // array
    String[] names = {"ABC", "XYZ", "zoo"};
    String s = names[1];
    names[1] = "cat";
    System.out.println(s); // s是"XYZ"还是"cat"? XYZ

    // String
    String s1 = "hello";
    String t = s1;
    s1 = "world";
    System.out.println(t); // t是"hello"还是"world"? hello

    // boolean
    int age = 7;
    // primary student的定义: 6~12岁
    boolean isPrimaryStudent = age > 6 && age < 12;
    //System.out.println(isPrimaryStudent);
    System.out.println("isPrimaryStudent=" + (isPrimaryStudent ? "Yes" : "No"));


    // in/out
//    Scanner scanner = new Scanner(System.in); // 创建Scanner对象
//    System.out.print("Input last score: "); // 打印提示
//    double lastScore = scanner.nextDouble(); // 读取一行输入并获取整数
//    System.out.print("Input your this score: "); // 打印提示
//    double score = scanner.nextDouble(); // 读取一行输入并获取整数
//
//    double diffPercent = (double) (score - lastScore) / lastScore;
//    System.out.printf("%.2f\n", diffPercent); // 显示两位小数3.14
//    System.out.printf("Improved is %.2f.\n", diffPercent); // 格式化输出
//    System.out.printf("Improved percentage is %2.2f%%.", diffPercent*100 ); // 格式化输出

    // for
    // Number of terms to include in the approximation
    int terms = 1000000; // You can adjust this for higher accuracy
    double pi = 0.0;

    // Loop through the series to calculate pi
    for (int i = 0; i < terms; i++) {
      // Leibniz formula: (-1)^i / (2i + 1), Math.pow(-1, i) = (-1)^i
      double term = Math.pow(-1, i) / (2 * i + 1);
      pi += term;
    }

    // Multiply the result by 4 to get the approximation of pi
    pi *= 4;

    // Output the result
    System.out.println("Approximation of Pi: " + pi);


    // sort - 冒泡排序 Bubble Sort
    int[] ns = {28, 12, 89, 73, 65, 18, 96, 50, 8, 36};
    // 排序前:
    System.out.println(Arrays.toString(ns));
    for (int i = 0; i < ns.length - 1; i++) {
      for (int j = 0; j < ns.length - i - 1; j++) {
        if (ns[j] > ns[j + 1]) {
          // 交换ns[j]和ns[j+1]:
          int tmp = ns[j];
          ns[j] = ns[j + 1];
          ns[j + 1] = tmp;
        }
      }
    }
    // 排序后:
    System.out.println(Arrays.toString(ns));

    // or using existing sort function
    Arrays.sort(ns);
    System.out.println(Arrays.toString(ns));

    int[][] ns2 = {{1, 2, 3, 4}, {5, 6}, {7, 8, 9}};
    System.out.println(Arrays.deepToString(ns2));


    // 用二维数组表示的学生成绩:
    int[][] scores = {
      {82, 90, 91}, // 学生甲的语数英成绩
      {68, 72, 64}, // 学生乙的语数英成绩
      {95, 91, 89}, // ...
      {67, 52, 60},
      {79, 81, 85},
    };
    for (int[] scoresByPerson : scores) {
      double sumByPerson = 0;
      for (int score : scoresByPerson) {
        sumByPerson += score;
      };
      double average = sumByPerson / 3;
      System.out.println("average=" + average);

    };

    // OOP basic1
    City bj = new City();
    bj.name = "Beijing";
    bj.latitude = 39.903;
    bj.longitude = 116.401;
    System.out.println("City: " + bj.name);
    System.out.println("Location: " + bj.latitude + ", " + bj.longitude);

    // OOP basic1
    Person ming = new Person();
    ming.setName("xiaoming");
    ming.setAge(12);
    System.out.println(ming.getAge());
    System.out.println("Person count=" + Person.getCount());

    // OOP construction method
    Person p = new Person("Xiao Ming", 15);
    System.out.println(p.getName());
    System.out.println(p.getAge());
    System.out.println("Person count=" + Person.getCount());

    // OOP - inherit
    // TODO: 定义PrimaryStudent，从Student继承，新增grade字段:
    PrimaryStudent ps = new PrimaryStudent("xiao jun", 9, 100, 5);
    System.out.println("new PrimaryStudent name is " + ps.getName()+ ", score is " + ps.getScore() + ", grade is " + ps.getGrade());
    System.out.println("Person count=" + Person.getCount());

    // HashMap
    HashMap<String, String> obj1 = new HashMap<String, String>();
    obj1.put("100", "test1");
    obj1.put("500", "test2");
    obj1.put("200", "test3");
    obj1.put("200", "test4");
    obj1.put("300", "test5");
    System.out.println("HashMap value are : " + obj1 ); // no order, can not have duplicated key (printed one only).

    for (Map.Entry<String, String> eachEntry: obj1.entrySet())
    {
      System.out.println("HashMap each Entry -  Key = " + eachEntry.getKey() + " Value = " + eachEntry.getValue());
    }

    // faker
    Faker fk=new Faker();
    System.out.println("fake name= " + fk.name().fullName());
    System.out.println("fake address= " + fk.address().cityName());

  };
};

class City {
  String name;
  double latitude;
  double longitude;
};

class Person {
  private String name;
  private int age;
  private static int count;

  public Person() {
    count+=1;
  };

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
    count+=1;
  }

  public static int getCount() {
    return count;
  };

  public String getName() {
    return name;
  };

  public void setName(String name) {
    this.name = name;
  };

  public int getAge() {
    return age;
  };

  public void setAge(int age) {
    this.age = age;
  };
};

class Student extends Person {
  protected int score;

  public Student(String name, int age, int score) {
    super(name, age);
    this.score = score;
  }

  public int getScore() { return score; }
}

class PrimaryStudent extends Student {
  protected int grade;

  public PrimaryStudent(String name, int age, int score, int grade) {
    super(name, age, score);
    this.grade = grade;
  }

  public int getGrade() { return grade; }
}
