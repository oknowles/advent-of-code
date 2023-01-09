use std::collections::LinkedList;

fn read_input(input: &str) -> Vec<Instruction> {
    input.lines().map(|l| {
        let mut no_op = false;
        let mut reg_addition = 0;
        if l.starts_with("noop") {
            no_op = true
        } else {
            reg_addition = l.split(" ").last().unwrap().parse::<i32>().expect("Not a number");
        }
        Instruction { no_op, reg_addition }
    }).collect()
}

#[derive(Clone)]
struct Instruction {
    no_op: bool,
    reg_addition: i32,
}

fn part_one(input: Vec<Instruction>) -> i32 {
    let mut reg_val = 1;
    let mut cur_cycle = 1;
    let mut result = 0;

    for instruction in input {
        cur_cycle += 1;
        if !instruction.no_op {
            if counting_cycle(cur_cycle) {
                result += cur_cycle * reg_val;
            }
            cur_cycle += 1;
            reg_val += instruction.reg_addition;
        }

        // Doing this at the end means we don't have to do anything after the for loop
        if counting_cycle(cur_cycle) {
            result += cur_cycle * reg_val;
        }
    }

    result
}

fn counting_cycle(cycle: i32) -> bool {
    (cycle - 20) % 40 == 0 && cycle <= 220
}

fn part_two(input: Vec<Instruction>) -> String {
    let mut sprite_pos = 1;
    let mut cur_cycle = 0;
    let mut result = String::new();
    draw_pixels(&mut result, sprite_pos, cur_cycle);

    for instruction in input {
        cur_cycle += 1;
        draw_pixels(&mut result, sprite_pos, cur_cycle);
        if !instruction.no_op {
            cur_cycle += 1;
            sprite_pos += instruction.reg_addition;
            draw_pixels(&mut result, sprite_pos, cur_cycle);
        }
    }

    result
}

fn draw_pixels(output: &mut String, sprite_pos: i32, cycle: i32) {
    if cycle >= 240 { return }
    if cycle > 0 && cycle % 40 == 0 { output.push('\n') }

    let mut pixel = '.';
    if i32::abs((cycle % 40) - sprite_pos) <= 1 {
        pixel = '#';
    }
    output.push(pixel);
}

pub fn main() {
    let input = read_input(include_str!("../../input/day10.txt"));
    let part_one = part_one(input.clone());
    let part_two = part_two(input.clone());
    println!("[Day 10] Part One: {}\tPart Two:\n{}", part_one, part_two);
}

mod tests {
    use crate::days::day10::{part_one, part_two, read_input};

    #[test]
    fn examples() {
        let input = read_input(include_str!("../../input/day10_example.txt"));
        let part_one_res = part_one(input.clone());
        assert_eq!(part_one_res, 13140);
        let part_two_res = part_two(input.clone());
        assert_eq!(
            part_two_res,
            "##..##..##..##..##..##..##..##..##..##..\n\
             ###...###...###...###...###...###...###.\n\
             ####....####....####....####....####....\n\
             #####.....#####.....#####.....#####.....\n\
             ######......######......######......####\n\
             #######.......#######.......#######....."
        );
    }
}