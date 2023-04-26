import matplotlib.pyplot as plt
import numpy as np

def plot_half_plane(a, b, c, color):
    if b != 0:
        plt.plot([-c/a, 0], [-c/b, -c/b - a/a*b], color=color)
    else:
        plt.axvline(x=-c/a, color=color)

# Define constraints
eq1 = (-1, -4, 0)
eq2 = (1, 1, -3)
eq3 = (1, 1, 1)
eq4 = (1, 1, -1)
eq5 = (2, 1, 0)

# Define plot size
plt.xlim([-6, 6])
plt.ylim([-6, 6])

# Plot the half-planes
plot_half_plane(*eq1, 'r')
plot_half_plane(*eq2, 'g')
plot_half_plane(*eq3, 'b')
plot_half_plane(*eq4, 'y')
plot_half_plane(*eq5, 'm')

# Fill feasible region
x, y = np.meshgrid(np.linspace(-6, 6, 100), np.linspace(-6, 6, 100))
plt.contourf(x, y, (eq1[0]*x + eq1[1]*y >= eq1[2]) & (eq2[0]*x + eq2[1]*y >= eq2[2]) & (eq3[0]*x + eq3[1]*y >= eq3[2]) & (eq4[0]*x + eq4[1]*y >= eq4[2]) & (eq5[0]*x + eq5[1]*y >= eq5[2]))

plt.show()