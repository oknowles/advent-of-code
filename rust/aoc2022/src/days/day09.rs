use std::arch::aarch64::vqabss_s32;
use std::collections::HashSet;
use std::str::FromStr;
use Movement::{Up, Down, Left, Right, UpLeft, UpRight, DownLeft, DownRight, None};

fn read_input(input: &str) -> Vec<Instruction> {
    input.lines().map(|l| {
        let mut parts = l.split(" ");
        let movement = Movement::from_str(parts.next().unwrap()).expect("Not a movement");
        let amount = parts.next().unwrap().parse::<u32>().expect("Not a number");
        Instruction { movement, amount }
    }).collect()
}

#[derive(Clone)]
#[derive(PartialEq)]
enum Movement {
    Up, Down, Left, Right, UpLeft, UpRight, DownLeft, DownRight, None,
}

impl FromStr for Movement {
    type Err = ();

    fn from_str(s: &str) -> Result<Self, Self::Err> {
        match s {
            "U" => Ok(Up),
            "D" => Ok(Down),
            "L" => Ok(Left),
            "R" => Ok(Right),
            _ => Err(()),
        }
    }
}

#[derive(Clone)]
struct Instruction {
    movement: Movement,
    amount: u32,
}

fn part_one(input: Vec<Instruction>) -> usize {
    compute_tail_visited(input, 2)
}

fn part_two(input: Vec<Instruction>) -> usize {
    compute_tail_visited(input, 10)
}

fn compute_tail_visited(input: Vec<Instruction>, rope_len: usize) -> usize {
    let mut visited = HashSet::new();
    let mut rope_positions = vec![(0,0); rope_len];
    visited.insert((0,0));
    for inst in input {
        for _ in 0..inst.amount {
            // Update head position
            rope_positions[0] = new_pos(rope_positions[0], inst.movement.clone());
            // Update each position in rope
            for i in 1..rope_len {
                let movement = get_movement(rope_positions[i - 1], rope_positions[i]);
                rope_positions[i] = new_pos(rope_positions[i], movement);
            }
            // Save tail pos
            visited.insert(rope_positions[rope_len - 1]);
        }
    }
    return visited.len()
}

fn new_pos(pos: (i32, i32), movement: Movement) -> (i32, i32) {
    match movement {
        Up => (pos.0 + 1, pos.1),
        Down => (pos.0 - 1, pos.1),
        Left => (pos.0, pos.1 - 1),
        Right => (pos.0, pos.1 + 1),
        UpLeft => (pos.0 + 1, pos.1 - 1),
        UpRight => (pos.0 + 1, pos.1 + 1),
        DownLeft => (pos.0 - 1, pos.1 - 1),
        DownRight => (pos.0 - 1, pos.1 + 1),
        None => (pos.0, pos.1),
    }
}

fn get_movement(head_pos: (i32, i32), tail_pos: (i32, i32)) -> Movement {
    let dif_x = head_pos.1 - tail_pos.1;
    let dif_y = head_pos.0 - tail_pos.0;

    let mut res = None;
    if dif_x == -2 && dif_y == -1 { res = DownLeft }
    if dif_x == -2 && dif_y == 0 { res = Left }
    if dif_x == -2 && dif_y == 1 { res = UpLeft }
    if dif_x == -2 && dif_y == 2 { res = UpLeft }
    if dif_x == -2 && dif_y == -2 { res = DownLeft }
    if dif_x == 2 && dif_y == -1 { res = DownRight }
    if dif_x == 2 && dif_y == 0 { res = Right }
    if dif_x == 2 && dif_y == 1 { res = UpRight }
    if dif_x == 2 && dif_y == 2 { res = UpRight }
    if dif_x == 2 && dif_y == -2 { res = DownRight }
    if dif_x == -1 && dif_y == 2 { res = UpLeft }
    if dif_x == 0 && dif_y == 2 { res = Up }
    if dif_x == 1 && dif_y == 2 { res = UpRight }
    if dif_x == -1 && dif_y == -2 { res = DownLeft }
    if dif_x == 0 && dif_y == -2 { res = Down }
    if dif_x == 1 && dif_y == -2 { res = DownRight }

    return res
}

pub fn main() {
    let input = read_input(include_str!("../../input/day09.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 09] Part One: {}\tPart Two: {}", part_one, part_two);
}

mod tests {
    use crate::days::day09::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day09_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 13);
        let part_two_res = part_two(input.clone());
        assert_eq!(part_two_res, 1);

        let input_2 = read_input(include_str!("../../input/day09_example_2.txt"));
        let part_two_res_2 = part_two(input_2);
        assert_eq!(part_two_res_2, 36);
    }
}