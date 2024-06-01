public class GradeList
{
  private Grade[] grades;
  private int actualNumberOfGrades;

  public GradeList(int maxNumberOfGrades)
  {
    this.grades = new Grade[maxNumberOfGrades];
    this.actualNumberOfGrades = 0;
  }

  public int size()
  {
    return actualNumberOfGrades;
  }

  public void addGrade(Grade grade)
  {
    addGrade(actualNumberOfGrades, grade);
  }

  public void addGrade(int index, Grade grade)
  {
    if(grade == null){
      throw new IllegalArgumentException("Null grade");
    }

    if (actualNumberOfGrades < grades.length)
    {
      for (int i = actualNumberOfGrades - 1; i >= index; i--)
      {
        grades[i + 1] = grades[i];
      }
      grades[index] = grade;
      actualNumberOfGrades++;
    }
  }

  public void removeGrade(int index)
  {
    for (int i = index; i < actualNumberOfGrades - 1; i++)
    {
      grades[i] = grades[i + 1];
    }
    actualNumberOfGrades--;
  }

  public Grade getGrade(int index)
  {
    return grades[index];
  }

  public Grade getMaxGrade()
  {
    String course = null;
    int max = Grade.LEGAL_GRADES[Grade.LEGAL_GRADES.length - 1] - 1;
    for (int i = 0; i < actualNumberOfGrades; i++)
    {
      if (grades[i].getGrade() > max)
      {
        max = grades[i].getGrade();
        course = grades[i].getCourse();
      }
    }
    if (actualNumberOfGrades < 1)
    {
      throw new IllegalStateException("Empty grade list");
    }
    return new Grade(max, course);
  }

  public Grade getMinGrade()
  {
    String course = null;
    int min = Grade.LEGAL_GRADES[0] + 1;
    for (int i = 0; i < actualNumberOfGrades; i++)
    {
      if (grades[i].getGrade() < min)
      {
        min = grades[i].getGrade();
        course = grades[i].getCourse();
      }
    }
    if (actualNumberOfGrades < 1)
    {
      throw new IllegalStateException("Empty grade list");
    }
    return new Grade(min, course);
  }

  public double getAverage()
  {
    int sum = 0;

    for (int i = 0; i < actualNumberOfGrades; i++)
    {
      sum += grades[i].getGrade();
    }
    if (actualNumberOfGrades < 1)
    {
      throw new IllegalStateException("Empty grade list");
    }
    double average = (double) sum / actualNumberOfGrades;
    return average;
  }

  public int getGradeCount(int grade)
  {
    int count = 0;
    for (int i = 0; i < actualNumberOfGrades; i++)
    {
      if (grades[i].getGrade() == grade)
      {
        count++;
      }
    }
    return count;
  }

  public String getGradeDistribution()
  {
    String s = "{";
    for (int i = 0; i < Grade.LEGAL_GRADES.length; i++)
    {
      s += Grade.LEGAL_GRADES[i] + " (" + getGradeCount(Grade.LEGAL_GRADES[i])
          + ")";
      if (i < Grade.LEGAL_GRADES.length - 1)
      {
        s += ", ";
      }
    }
    s += "}";
    return s;
  }

  public String toString()
  {
    String s = "{";
    for (int i = 0; i < actualNumberOfGrades; i++)
    {
      s += grades[i];
      if (i < actualNumberOfGrades - 1)
      {
        s += ", ";
      }
    }
    s += "}";
    return s;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof GradeList))
    {
      return false;
    }
    GradeList other = (GradeList) obj;
    if (actualNumberOfGrades != other.actualNumberOfGrades)
    {
      return false;
    }
    for (int i = 0; i < actualNumberOfGrades; i++)
    {
      if (!grades[i].equals(other.grades[i]))
      {
        return false;
      }
    }
    return true;
  }

  public void removeGrade(Grade grade)
  {
    for (int i = 0; i < actualNumberOfGrades; i++)
    {
      if (grades[i].equals(grade))
      {
        for (int j = i; j < actualNumberOfGrades - 1; j++)
        {
          grades[j] = grades[j + 1];
        }
        actualNumberOfGrades--;
        break;
      }
    }
  }
}
