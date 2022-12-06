fn read_input(input: &str) -> Input {
    let mut split_input = input.split("\n\n");
    let stack_input = split_input.next().unwrap();
    let instruction_input = split_input.next().unwrap();
    let stacks = parse_stacks(stack_input);
    let instructions = parse_instructions(instruction_input);

    return Input { stacks, instructions }
}

fn parse_stacks(input: &str) -> Vec<Stack> {
    let stack_labels = input.lines().rev().next().unwrap();
    let stack_counts = stack_labels
        .split(' ')
        .last()
        .unwrap()
        .parse::<usize>().expect("Not a number");

    let mut stacks = vec![Stack(Vec::new()); stack_counts];
    input.lines().rev().skip(1).for_each(|l| {
        if !l.starts_with(" 1") {
            let chars = l.chars();
            l.match_indices('[').for_each(|(i, _)| {
                stacks[i / 4].0.push(chars.clone().nth(i + 1).unwrap());
            })
        }
    });
    stacks
}

fn parse_instructions(input: &str) -> Vec<Instruction> {
    input.lines().map(|l| {
        let instruction = l.split(' ').collect::<Vec<&str>>();
        Instruction {
            stack_count: instruction[1].parse::<usize>().unwrap(),
            origin: instruction[3].parse::<usize>().unwrap() - 1,
            destination: instruction[5].parse::<usize>().unwrap() - 1,
        }
    }).collect()
}

fn part_one(input: &Input) -> String {
    let mut stacks = input.stacks.clone();
    for instruction in &input.instructions {
        for _ in 0..instruction.stack_count {
            let popped = stacks[instruction.origin].pop();
            stacks[instruction.destination].push(popped);
        }
    }
    String::from_iter(stacks.iter().map(|x| x.clone().pop()))
}

fn part_two(input: &Input) -> String {
    let mut stacks = input.stacks.clone();
    for instruction in &input.instructions {
        let mut temp_stack = Vec::new();
        for _ in 0..instruction.stack_count {
            let popped = stacks[instruction.origin].pop();
            temp_stack.push(popped);
        }
        temp_stack.reverse();
        stacks[instruction.destination].0.append(&mut temp_stack);
    }
    String::from_iter(stacks.iter().map(|x| x.clone().pop()))
}

#[derive(Clone)]
struct Stack(Vec<char>);

impl Stack {
    fn push(&mut self, c: char) {
        self.0.push(c)
    }

    fn pop(&mut self) -> char {
        self.0.pop().unwrap()
    }
}

#[derive(Clone)]
struct Input {
    stacks: Vec<Stack>,
    instructions: Vec<Instruction>,
}

#[derive(Clone)]
struct Instruction {
    stack_count: usize,
    origin: usize,
    destination: usize,
}

pub fn main() {
    let input = read_input(include_str!("../../input/day05.txt"));
    let part_one = part_one(&input);
    let part_two = part_two(&input);
    println!("[Day 05] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day05::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day05_example.txt"));
        let part_one_res = part_one(&input);
        assert_eq!(part_one_res, "CMZ");
        let part_two_res = part_two(&input.clone());
        assert_eq!(part_two_res, "MCD");
    }
}