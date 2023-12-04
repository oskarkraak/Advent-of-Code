#include <algorithm>
#include <fstream>
#include <iostream>
#include <iterator>
#include <map>
#include <ostream>
#include <set>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

// Implement cout for vectors
template <typename S>
ostream &operator<<(ostream &os, const vector<S> &vector) {
  os << "[\"";
  for (int i = 0; i < vector.size(); i++) {
    auto element = vector[i];
    os << element;
    if (i != vector.size() - 1) {
      os << "\", \"";
    }
  }
  os << "\"]";
  return os;
}

vector<string> split(string s, string delimiter) {
  vector<string> result;
  size_t start = 0;
  size_t end = s.find(delimiter);
  while (end != string::npos) {
    result.push_back(s.substr(start, end - start));
    start = end + delimiter.length();
    end = s.find(delimiter, start);
  }
  result.push_back(s.substr(start)); // Add the last token
  return result;
}

void part1(vector<vector<vector<string>>> input) {
  const int NUM_RED = 12, NUM_GREEN = 13, NUM_BLUE = 14;
  int result = 0;
  for (int i = 0; i < input.size(); i++) {
    vector<vector<string>> game = input[i];
    int max_red = 0, max_green = 0, max_blue = 0;
    for (vector<string> set : game) {
      for (string cubes : set) {
        int cube_count = stoi(split(cubes, " ")[0]);
        string cube_color = split(cubes, " ")[1];
        if (cube_color[0] == 'r') {
          max_red = max(max_red, cube_count);
        } else if (cube_color[0] == 'g') {
          max_green = max(max_green, cube_count);
        } else {
          max_blue = max(max_blue, cube_count);
        }
      }
    }
    if (max_red <= NUM_RED && max_green <= NUM_GREEN && max_blue <= NUM_BLUE) {
      result += i + 1;
    }
  }
  cout << "Part 1: " << result << endl;
}

void part2(vector<vector<vector<string>>> input) {
  int result = 0;
  cout << "Part 2: " << result << endl;
}

vector<string> read_input() {
  ifstream input_file;
  input_file.open("input.txt");
  vector<string> input;
  while (input_file.good()) {
    string line;
    getline(input_file, line);
    input.push_back(line);
  }
  return input;
}

vector<vector<vector<string>>> parse_input(vector<string> input) {
  vector<vector<vector<string>>> parsed;
  for (string game : input) {
    game = split(game, ": ")[1];
    vector<string> setStrings = split(game, "; ");
    vector<vector<string>> sets;
    for (string setString : setStrings) {
      vector<string> set = split(setString, ", ");
      sets.push_back(set);
    }
    parsed.push_back(sets);
  }
  return parsed;
}

int main() {
  ios_base::sync_with_stdio(0);
  vector<string> input = read_input();
  vector<vector<vector<string>>> parsed_input = parse_input(input);
  // vectors are pass by value, no copying needed
  part1(parsed_input);
  part2(parsed_input);
  return 0;
}
