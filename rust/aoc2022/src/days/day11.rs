fn read_input(input: &str) -> Vec<Monkey> {
    input.lines().collect::<Vec<&str>>().chunks(7).map(|c| {
        let id = c[0].split(" ").last().unwrap().parse::<usize>().expect("Not a number");
        let starting_items = c[1].split_at(18).1.split(", ").map(|x| x.parse::<usize>().expect("Not a number")).collect();
        let divisible_test_val = c[3].split_at(21).1.parse::<usize>().expect("Not a number");
        let test_true_monkey = c[4].split_at(29).1.parse::<usize>().expect("Not a number");
        let test_false_monkey = c[5].split_at(30).1.parse::<usize>().expect("Not a number");

        let mut operator_str = c[2].split_at(23).1.split(" ");
        let operator_symbol = operator_str.next().unwrap();
        let operator_on = operator_str.last().unwrap();

        Monkey {
            id,
            items: starting_items,
            operator_symbol: operator_symbol.to_owned(),
            operator_subject: operator_on.to_owned(),
            divisible_test_val,
            test_true_monkey,
            test_false_monkey,
        }
    }).collect()
}

#[derive(Clone)]
struct Monkey {
    id: usize,
    items: Vec<usize>,
    operator_symbol: String,
    operator_subject: String,
    divisible_test_val: usize,
    test_true_monkey: usize,
    test_false_monkey: usize,
}

impl Monkey {
    fn receiving_monkey(&self, item_val: usize) -> usize {
        if item_val % self.divisible_test_val == 0 {
            self.test_true_monkey
        } else {
            self.test_false_monkey
        }
    }

    fn apply_operation(&self, old: usize) -> usize {
        let mut operator_subject = old;
        if self.operator_subject != "old" {
            operator_subject = self.operator_subject.parse::<usize>().expect("Not a number");
        }
        if self.operator_symbol == String::from("+") {
            old + operator_subject
        } else if self.operator_symbol == String::from("*") {
            old * operator_subject
        } else {
            panic!("Unsupported operation type")
        }
    }

    fn receive_item(&mut self, item_val: usize) {
        self.items.push(item_val)
    }

    fn clear_items(&mut self) {
        self.items.clear()
    }
}

// Monkeys always throw their items so we can clear their list at the end of each go
fn part_one(input: Vec<Monkey>) -> usize {
    let mut monkeys = input.clone();
    let mut inspections: Vec<usize> = vec![0; monkeys.len()];
    for _ in 0..20 {
        for cur_monkey in 0..monkeys.len() {
            let monkey = &mut monkeys[cur_monkey];
            for item in &monkey.items {
                // inspects (update worry level using operator) then gets bored (divide by 3)
                let new_item_val = monkey.apply_operation(*item) / 3;
                let monkey_to_pass_to = monkey.receiving_monkey(new_item_val);
                monkeys[monkey_to_pass_to].receive_item(new_item_val);
            }
            inspections[monkey.id] += monkey.items.len();
            monkeys[cur_monkey].clear_items();
        }
    }
    inspections.sort();
    inspections[0] * inspections[1]
}

fn part_two(input: Vec<Monkey>) -> usize {
    0
}

pub fn main() {
    let input = read_input(include_str!("../../input/day11.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 11] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day11::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day11_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 10605);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 1);
    }
}