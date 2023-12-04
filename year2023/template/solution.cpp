#include <algorithm>
#include <fstream>
#include <iostream>
#include <map>
#include <ostream>
#include <set>
#include <string>
#include <vector>

using namespace std;

// Implement cout for vectors
template <typename S>
ostream &operator<<(ostream &os, const vector<S> &vector) {
  os << "[";
  for (int i = 0; i < vector.size(); i++) {
    auto element = vector[i];
    os << element;
    if (i != vector.size() - 1) {
      os << ", ";
    }
  }
  os << "]";
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

void part1(vector<string> input) {
  int result = 0;
  cout << "Part 1: " << result << endl;
}

void part2(vector<string> input) {
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

int main() {
  ios_base::sync_with_stdio(0);
  vector<string> input = read_input();
  // vectors are pass by value, no copying needed
  part1(input);
  part2(input);
  return 0;
}
