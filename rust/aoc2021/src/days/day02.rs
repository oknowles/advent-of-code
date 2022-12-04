enum Direction {
    Forward,
    Up,
    Down,
}

fn read_input(input: &str) -> Vec<(Direction, i32)> {
    input.lines().map(|l| map_line(l)).collect()
}

fn map_line(line: &str) -> (Direction, i32) {
    let pair: Vec<&str> = line.split(' ').collect();
    let direction = match pair[0] {
        "forward" => Direction::Forward,
        "up" => Direction::Up,
        "down" => Direction::Down,
        _ => panic!("unexpected input"),
    };
    let velocity = pair[1].parse::<i32>().unwrap();
    (direction, velocity)
}

fn part_one(input: &Vec<(Direction, i32)>) -> i32 {
    let mut horizontal_pos: i32 = 0;
    let mut depth: i32 = 0;
    for i in 0..input.len() {
        match input[i].0 {
            Direction::Forward => horizontal_pos += input[i].1,
            Direction::Up => depth -= input[i].1,
            Direction::Down => depth += input[i].1,
        };
    }
    horizontal_pos * depth
}

fn part_two(input: &Vec<(Direction, i32)>) -> i32 {
    let mut horizontal_pos: i32 = 0;
    let mut depth: i32 = 0;
    let mut aim: i32 = 0;
    for i in 0..input.len() {
        match input[i].0 {
            Direction::Forward => {
                horizontal_pos += input[i].1;
                depth += aim * input[i].1;
            }
            Direction::Up => aim -= input[i].1,
            Direction::Down => aim += input[i].1,
        };
    }
    horizontal_pos * depth
}

pub fn main() {
    let input = read_input(include_str!("../../input/day02.txt"));
    let part1 = part_one(&input);
    let part2 = part_two(&input);
    println!("[Day 02] Part1: {}\tPart2: {}", part1, part2);
}

mod tests {
    use days::day02::{part_one, part_two, Direction};

    #[test]
    fn examples() {
        let input = Vec::from([
            (Direction::Forward, 5),
            (Direction::Down, 5),
            (Direction::Forward, 8),
            (Direction::Up, 3),
            (Direction::Down, 8),
            (Direction::Forward, 2),
        ]);
        let part_one_res = part_one(&input);
        assert_eq!(part_one_res, 150);
        let part_two_res = part_two(&input);
        assert_eq!(part_two_res, 900);
    }
}
