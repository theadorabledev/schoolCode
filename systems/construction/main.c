#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
struct person {
  char name[128];
  int age;
  char class[128];
};
char* first_names[] = {
  "Novella",
  "Robyn",
  "Jenette",
  "Yee",
  "Toni",
  "Kyong",
  "Jose",
  "Lawerence",
  "Beatriz",
  "Chasity"
};
char* last_names[] = {
  "Skullsky",
  "Ironkeep",
  "Spiritwalker",
  "Plainaxe",
  "Humbledancer",
  "Sunlance",
  "Crystalwinds",
  "Dreamflower",
  "Rosewater",
  "Wildwatcher"
};
char* classes[] = {
  "proletariat",
  "bourgeoisie"
};
struct person new_person() {
  struct person individual;
  sprintf(individual.name, "%s %s", first_names[rand() % 10], last_names[rand() % 10]);
  individual.age = rand() % 70 + 18;
  sprintf(individual.class, "%s", classes[rand() % 2]);
  return individual;
}
void print_person(struct person p){
  printf("Name: %s\nAge: %d\nClass: %s\n", p.name, p.age, p.class);
};
void defect(struct person* p){
  if(!strcmp(p->class, "proletariat")){
    strcpy(p->class, "bourgeoisie");
  }else{
    strcpy(p->class, "proletariat");
  }
}
int main(){
  srand(time(0));
  struct person people[10];
  for(int i = 0; i < 10; i++){
    printf("--------------------\n");
    people[i] = new_person();
    print_person(people[i]);
    printf("\nDefecting:\n");
    defect(&people[i]);
    print_person(people[i]);
  }
  return 0;
}
