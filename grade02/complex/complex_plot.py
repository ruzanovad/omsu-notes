import matplotlib.pyplot as plt
import numpy as np

# Создаем комплексную плоскость
x, y = np.linspace(-2, 2, 500), np.linspace(-2, 2, 500)
X, Y = np.meshgrid(x, y)
Z = X + 1j*Y

# Условия разрезов
cut1 = abs(Z-1/2) == 1
cut2 = abs(Z+1/2) == 1
cut3 = np.imag(Z) < 0

# Условие выбора правой полуплоскости
cond = np.real(Z) > 0

# Применяем условия разрезов
Z[cut1 & cut3] = np.nan + 1j*np.nan
Z[cut2 & cut3] = np.nan + 1j*np.nan

# Отображаем результат
plt.figure(figsize=(10, 5))
plt.subplot(121)
plt.title('Комплексная плоскость')
plt.imshow(cond.T, extent=[np.min(x), np.max(x), np.min(y), np.max(y)], cmap='gray')
plt.axis('off')
plt.subplot(122)
plt.title('Образ правой полуплоскости')
plt.imshow(np.imag(Z).T, extent=[np.min(x), np.max(x), np.min(y), np.max(y)], cmap='jet')
plt.axis('off')
plt.show()
