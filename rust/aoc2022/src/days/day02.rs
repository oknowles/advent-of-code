use std::str::FromStr;

fn read_input(input: &str) -> Vec<(&str, &str)> {
    input.lines()
        .map(|l| {
            let line = l.split(' ').collect::<Vec<&str>>();
            (line[0], line[1])
        })
        .collect()
}

fn part_one(input: Vec<(&str,&str)>) -> i32 {
    input
        .iter()
        .map(|(x,y)| (Choice::from_str(x).unwrap(), Choice::from_str(y).unwrap()))
        .map(|(x,y)| compute_score(x, y))
        .sum()
}

fn part_two(input: Vec<(&str, &str)>) -> i32 {
    input
        .iter()
        .map(|(x,y)| (Choice::from_str(x).unwrap(), compute_our_choice(x, y)))
        .map(|(x,y)| compute_score(x, y))
        .sum()
}

#[derive(PartialEq)]
enum Outcome {
    Win, Loss, Draw
}

impl FromStr for Outcome {
    type Err = ();

    fn from_str(s: &str) -> Result<Outcome, Self::Err> {
        match s {
            "X" => Ok(Outcome::Loss),
            "Y" => Ok(Outcome::Draw),
            "Z" => Ok(Outcome::Win),
            _ => Err(())
        }
    }
}

#[derive(PartialEq)]
enum Choice {
    Rock, Paper, Scissors
}

impl FromStr for Choice {
    type Err = ();

    fn from_str(s: &str) -> Result<Choice, Self::Err> {
        match s {
            "A" | "X" => Ok(Choice::Rock),
            "B" | "Y" => Ok(Choice::Paper),
            "C" | "Z" => Ok(Choice::Scissors),
            _ => Err(())
        }
    }
}

fn compute_our_choice(opponent_choice_str: &str, outcome_str: &str) -> Choice {
    let opponent_choice = Choice::from_str(opponent_choice_str).unwrap();
    let outcome  = Outcome::from_str(outcome_str).unwrap();
    if outcome == Outcome::Draw {
        return opponent_choice
    }
    match opponent_choice {
        Choice::Rock => if outcome == Outcome::Win { Choice::Paper } else { Choice::Scissors }
        Choice::Paper => if outcome == Outcome::Win { Choice::Scissors } else { Choice::Rock }
        Choice::Scissors => if outcome == Outcome::Win { Choice::Rock } else { Choice::Paper }
    }
}

fn compute_score(opponent_choice: Choice, our_choice: Choice) -> i32 {
    let mut score = 0;
    match our_choice {
        Choice::Rock => score += 1,
        Choice::Paper => score += 2,
        Choice::Scissors => score += 3
    }
    match compute_outcome(opponent_choice, our_choice) {
        Outcome::Win => score += 6,
        Outcome::Draw => score += 3,
        Outcome::Loss => {}
    }
    score
}

fn compute_outcome(opponent_choice: Choice, our_choice: Choice) -> Outcome {
    if (our_choice == Choice::Rock && opponent_choice == Choice::Scissors) ||
        (our_choice == Choice::Paper && opponent_choice == Choice::Rock) ||
        (our_choice == Choice::Scissors && opponent_choice == Choice::Paper) {
        Outcome::Win
    } else if our_choice == opponent_choice {
        Outcome::Draw
    } else {
        Outcome::Loss
    }
}

pub fn main() {
    let input = read_input(include_str!("../../input/day02.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 02] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day02::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day02_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 15);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 12);
    }
}