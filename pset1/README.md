# algorithem-princeton
## Pset 1
* [Lecture slides](https://d3c33hcgiwev3.cloudfront.net/_b65e7611894ba175de27bd14793f894a_15UnionFind.pdf?Expires=1493510400&Signature=UOwElU55sfSAb73sXOUxNuFuvq3fzO62qKYJBTMK6jF23GM6PmAC~ebcsGa7OG8l695RuqEDUuwe6Vab5~UCpS1ZuuUAvv1cKB3xidBczqun~O-Q7-8wGBhdealxIooEC8YM2~1WV7G~kW5EQIv-AVfd4cxTzWKf1YxdgOuNzuU_&Key-Pair-Id=APKAJLTNE6QMUY6HBC5A)

# Union Find
* UF(int N) initialzie union-find data structure with N objects
* void union(int p, int q) add connection between p and q
* boolean connected(int p, int q)

# Quick find
* Integer array id[] of length N
* iff := if and only if
* p and q are connected iff they have the same id
* Too expensive: it takes N^2 array accesses to process a sequence of N union commands on N objects
* Quadratic algorithm don't scale with technology

# Quick union
* Integer array id[] of length N.
* Interpeataion: id[i] is parent of i
* Root of i is id[id......], keep going unitl it doesn't change(algorithm ensures no cycles)
## Lazy approach
* Data structure: Integer array id[] of length N. Interpreation: id[i] is parent of i. Root of i is id[id[id[i]...]]
* Find: check if p and q have the same root.
* Union. To merge components containing p and q, set the id of p's root to the id of q's root 
* Quick runion is too expensive: find can be N array accesses

# Improvement
## Weighted quick-union
* Modify quick-union to avoid tall trees
* Keep track of size of each tree (number of objects).
* Balance by linking root of smaller tree to root of larger tree, reasonable alternativees: union by height 
* Data strucutre: same of quick-union, but maintain extra array sz[i] to count number of ojbects in the tree rooted at i.
* Identical to quick-union
* Union: Modify quick-union to: link root of smaller tree to root of larger tree.
* Update the sz[] array.
* Weighted QU takes logarithmic time for union and connected

# Improvement2
* Quick union with path compression: Just after computing the root of p, set the id of each examined node to point to that root.
* Proposition: Starting from an empty data strucutre, any sequence of M union-find ops on N
objects makes <= c(N + Mlg*N) array accesses.
* lg* := iterate log function

# Style
* Include a bold(or javadoc) comment describing every method
* Incldue a comment describing every instance variable
* Indent consistently
