#ifndef SM2026_LAB2_H_
#define SM2026_LAB2_H_

#include <SDL2/SDL.h>

void setPixel(int x, int y, Uint8 R, Uint8 G, Uint8 B);
SDL_Color getPixel(int x, int y);
Uint8 normalizeUint8(float val);

#endif  // SM2026_LAB2_H_